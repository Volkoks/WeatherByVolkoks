package com.example.weatherbyvolkoks.presenter;


import android.widget.ImageView;

import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.Constants;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.ILoaderWeather5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.LoaderWeather5day;
import com.squareup.picasso.Picasso;

import retrofit2.Response;

public class PresenterForMainActivity implements ILoaderWeather5Day, IPresenterForMainAct.ForCitySelection, IPresenterForMainAct.ForPresenter {
    // - поля:
    private String base_city;

    // - интерфейсы:
    private final IPresenterForMainAct.ForView IForView;

    public PresenterForMainActivity(String city, IPresenterForMainAct.ForView iForView) {
        this.base_city = city;
        this.IForView = iForView;
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
    public void ADError(String title, String error) {

    }

    @Override
    public void getCity(String city) {
        base_city = city;
    }
}
