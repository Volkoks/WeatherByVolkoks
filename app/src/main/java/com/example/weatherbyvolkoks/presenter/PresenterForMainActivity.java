package com.example.weatherbyvolkoks.presenter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.ListWeather;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.ILoaderWeather5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.LoaderWeather5day;
import com.example.weatherbyvolkoks.ui.WeatherForecastAdapter;
import com.squareup.picasso.Picasso;

import retrofit2.Response;

public class PresenterForMainActivity implements ILoaderWeather5Day, IPresenterForMainAct.ForPresenter {
    private String base_city;

    private final IPresenterForMainAct.ForView IForView;
    private final Context context;

    public PresenterForMainActivity(String city, IPresenterForMainAct.ForView iForView, Context context) {
        this.base_city = city;
        this.IForView = iForView;
        this.context = context;
        initWeatherToAPI(base_city);
    }

    private void initWeatherToAPI(String city) {
        LoaderWeather5day loaderWeather5day = new LoaderWeather5day(this);
        loaderWeather5day.downloadWeather(city);
    }


    @Override
    public void weatherLoadFor5Day(Response<WeatherRequest5Day> response) {
        IForView.setListView(response.body());

    }

    @Override
    public void weatherImageInit(WeatherRequest5Day request5Day, ImageView iconWeather) {
        String main = request5Day.getListWeathers()[0].getWeather()[0].getMain();
        switch (main) {
            case "Clouds":
                Picasso.get().load(R.drawable.overcast).into(iconWeather);
                break;
            case "Rain":
                Picasso.get().load(R.drawable.showers).into(iconWeather);
                break;
            case "Snow":
                Picasso.get().load(R.drawable.snows).into(iconWeather);
                break;
            case "Clear":
                Picasso.get().load(R.drawable.cleare).into(iconWeather);
                break;
            case "Drizzle":
                Picasso.get().load(R.drawable.showersscattered).into(iconWeather);
                break;
            case "Thunderstorm":
                Picasso.get().load(R.drawable.violentstorm).into(iconWeather);
                break;
            default:
                Picasso.get().load(R.drawable.severealert).into(iconWeather);
                break;
        }

    }

    @Override
    public void initAlertDialogAboutApp(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.about_app)
                .setMessage(R.string.about_app_message)
                .setCancelable(false)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context.getApplicationContext(), "Спасибо что выбрали нас!)", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void initAdapterAndRecyclerView(RecyclerView recyclerView, ListWeather[] listWeather) {
        WeatherForecastAdapter weatherForecastAdapter = new WeatherForecastAdapter(listWeather);
        initRecyclerView(recyclerView,weatherForecastAdapter);
    }

    private void initRecyclerView(RecyclerView thisRecyclerView,WeatherForecastAdapter adapter) {
        RecyclerView recyclerView = thisRecyclerView;
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void ADError(String title, String error) {

    }

}
