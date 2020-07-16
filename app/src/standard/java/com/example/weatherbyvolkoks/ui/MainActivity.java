package com.example.weatherbyvolkoks.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherbyvolkoks.BaseActivity;

import com.example.weatherbyvolkoks.GetCityes;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.ListWeather;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.ILoaderWeather5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.LoaderWeather5day;


import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;

import com.squareup.picasso.Picasso;

import retrofit2.Response;

import static com.example.weatherbyvolkoks.R.*;
import static java.lang.String.format;

public class MainActivity extends BaseActivity implements GetCityes, ILoaderWeather5Day {
    private static String mainCity = "Moscow";

    private final static int REQUEST_CODE = 1;
    private final static int SETTING_CODE = 2;


    private TextView city;
    private TextView temperature;
    private TextView description;
    private TextView temp_max_min;
    private ImageView iconWeather;
    private TextView humidity, humidity2;
    private TextView wind, wind2;
    private TextView pressure, pressure2;
    private ImageView imageHumidity, imageWind, imagePressure;
    private Button testVisibleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        initGUI();
        initWeatherToAPI();


        testVisibleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibilityOfFAdvancedOptions();
            }
        });

    }

    private void initWeatherToAPI() {
        LoaderWeather5day loaderWeather5day = new LoaderWeather5day(this);
        loaderWeather5day.downloadWeather(mainCity);
    }

    private void initGUI() {
        city = findViewById(id.City);
        temperature = findViewById(id.Temperature);
        description = findViewById(id.weather_description);
        iconWeather = findViewById(id.iconWeatherView);
        temp_max_min = findViewById(id.temp_max_min);
        humidity = findViewById(id.humidity_textView);
        wind = findViewById(id.wind_textView);
        pressure = findViewById(id.pressure_textView);
        humidity2 = findViewById(id.humidity_textView2);
        wind2 = findViewById(id.wind_textView2);
        pressure2 = findViewById(id.pressure_textView2);
        imageHumidity = findViewById(id.humidity_imageView);
        imageWind = findViewById(id.wind_imageView);
        imagePressure = findViewById(id.pressure_image);
        testVisibleBtn = findViewById(id.test_visible_btn);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar card_view_for_weather_forecast_5_day clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                activityTransitionIntent(ScreenSetting.class, SETTING_CODE);
                break;
            case R.id.enter_city_selection2:
                activityTransitionIntent(CitySelectionScreen.class, REQUEST_CODE);
                break;
            case R.id.refresh_the_weather:
                initWeatherToAPI();
                break;
            case R.id.about_app:
                initAlertDialogAboutApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void activityTransitionIntent(Class youClass, int RequestCode) {
        Intent intent = new Intent(getApplicationContext(), youClass);
        startActivityForResult(intent, RequestCode);
        return;
    }

    private void initRecyclerView(WeatherForecastAdapter adapter) {
        RecyclerView recyclerView = findViewById(id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    private void initAdapterAndRecyclerView(ListWeather[] listWeather) {
            WeatherForecastAdapter weatherForecastAdapter = new WeatherForecastAdapter(listWeather);
            initRecyclerView(weatherForecastAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE) {
            recreate();
        }
        if (requestCode == REQUEST_CODE && data != null) {
            if (resultCode == RESULT_OK) {
                Parcel parcel = (Parcel) data.getSerializableExtra("parcel");
                assert parcel != null;
                city.setText(parcel.cityName);
                mainCity = parcel.weatherCityName;
                initWeatherToAPI();
            }
        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void weatherLoadFor5Day(Response<WeatherRequest5Day> response) {
        initAdapterAndRecyclerView(response.body().getListWeathers());

        int valueTemperature = (int) response.body().getListWeathers()[0].getMain().getTemp();
        int valueTempMax = (int) response.body().getListWeathers()[0].getMain().getTemp_max();
        int valueTempMin = (int) response.body().getListWeathers()[0].getMain().getTemp_min();
        int valueHumidity = response.body().getListWeathers()[0].getMain().getHumidity();
        int valueWind = (int) response.body().getListWeathers()[0].getWind().getSpeed();
        int valuePressure = response.body().getListWeathers()[0].getMain().getPressure();

        city.setText(response.body().getCity().getName());
        temperature.setText(valueTemperature + "\u2103");
        temp_max_min.setText(format("%d/%d" + "\u2103", valueTempMax, valueTempMin));
        description.setText(response.body().getListWeathers()[0].getWeather()[0].getDescription());
        humidity.setText(valueHumidity + "%");
        wind.setText(valueWind + "m/s");
        pressure.setText(valuePressure + "hPa");
        weatherImageInit(response);
    }

    private void weatherImageInit(Response<WeatherRequest5Day> response) {
        String main = response.body().getListWeathers()[0].getWeather()[0].getMain();
        switch (main) {
            case "Clouds":
                Picasso.get().load(drawable.overcast).into(iconWeather);
                break;
            case "Rain":
                Picasso.get().load(drawable.showers).into(iconWeather);
                break;
            case "Snow":
                Picasso.get().load(drawable.snows).into(iconWeather);
                break;
            case "Clear":
                Picasso.get().load(drawable.cleare).into(iconWeather);
                break;
            case "Drizzle":
                Picasso.get().load(drawable.showersscattered).into(iconWeather);
                break;
            case "Thunderstorm":
                Picasso.get().load(drawable.violentstorm).into(iconWeather);
                break;
            default:
                Picasso.get().load(drawable.severealert).into(iconWeather);
                break;
        }
    }

    @Override
    public void ADError(String title, String error) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(title)
                        .setMessage(error);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void initAlertDialogAboutApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(string.about_app)
                .setMessage(string.about_app_message)
                .setCancelable(false)
                .setPositiveButton(string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Спасибо что выбрали нас!)", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public String getCity() {
        return mainCity;
    }

    private void visibilityOfFAdvancedOptions() {
        humidity.setVisibility(View.VISIBLE);
        humidity2.setVisibility(View.VISIBLE);
        wind.setVisibility(View.VISIBLE);
        wind2.setVisibility(View.VISIBLE);
        pressure.setVisibility(View.VISIBLE);
        pressure2.setVisibility(View.VISIBLE);
        imageHumidity.setVisibility(View.VISIBLE);
        imagePressure.setVisibility(View.VISIBLE);
        imageWind.setVisibility(View.VISIBLE);
    }
}
