package com.example.weatherbyvolkoks.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherbyvolkoks.BaseActivity;

import com.example.weatherbyvolkoks.GetCityes;
import com.example.weatherbyvolkoks.data.loaderWeather.ILoaderWeather;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeather;

import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.Soc.SocSourceBuilder;
import com.example.weatherbyvolkoks.data.Soc.SocialDataSource;
import com.example.weatherbyvolkoks.data.WeatherAPI_1day.WeatherRequest;
import com.squareup.picasso.Picasso;

import retrofit2.Response;

import static com.example.weatherbyvolkoks.R.*;
import static java.lang.String.format;

public class MainActivity extends BaseActivity implements ILoaderWeather, GetCityes {
    private static String mainCity = "Moscow";
    private final static int REQUEST_CODE = 1;
    private final static int SETTING_CODE = 2;

    private TextView city;
    private TextView temperature;
    private TextView description;
    private TextView temp_max_min;
    private ImageView iconWeather;
    private TextView humidity;
    private TextView wind;
    private TextView pressure;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        SocialDataSource sourceData = new SocSourceBuilder().setResources(getResources()).build();
        initGUI();
        initRecyclerView(sourceData);
        initWeatherToAPI();

    }

    private void initWeatherToAPI() {
        LoaderWeather loaderWeather = new LoaderWeather(this);
        loaderWeather.downloadWeather(mainCity);
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
                Intent intent = new Intent(getApplicationContext(), ScreenSetting.class);
                startActivityForResult(intent, SETTING_CODE);
                break;
            case R.id.enter_city_selection2:
                Intent intent2 = new Intent(getApplicationContext(), CitySelectionScreen.class);
                startActivityForResult(intent2, REQUEST_CODE);
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

    private void initRecyclerView(SocialDataSource data) {

        RecyclerView recyclerView = findViewById(id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        WeatherAdapter adapter = new WeatherAdapter(data);
        recyclerView.setAdapter(adapter);
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

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void activate(Response<WeatherRequest> response) {
        int valueTemperature = (int) response.body().getMain().getTemp();
        int valueTempMax = (int) response.body().getMain().getTemp_max();
        int valueTempMin = (int) response.body().getMain().getTemp_min();
        int valueHumidity = response.body().getMain().getHumidity();
        int valueWind = (int) response.body().getWind().getSpeed();
        int valuePressure = response.body().getMain().getPressure();

        city.setText(response.body().getName());
        temperature.setText(valueTemperature + "\u2103");
        description.setText(response.body().getWeathers()[0].getDescription());
        temp_max_min.setText(format("%d/%d" + "\u2103", valueTempMax, valueTempMin));
        humidity.setText(valueHumidity+"%");
        wind.setText(valueWind+"m/s");
        pressure.setText(valuePressure+"hPa");


        if (responseGetMain(response, "Clouds")) {
            Picasso.get().load(drawable.overcast).into(iconWeather);
        } else if (responseGetMain(response, "Rain")) {
            Picasso.get().load(drawable.showers).into(iconWeather);
        } else if (responseGetMain(response, "Snow")) {
            Picasso.get().load(drawable.snows).into(iconWeather);
        } else if (responseGetMain(response, "Clear")) {
            Picasso.get().load(drawable.cleare).into(iconWeather);
        } else if (responseGetMain(response, "Drizzle")) {
            Picasso.get().load(drawable.showersscattered).into(iconWeather);
        } else if (responseGetMain(response, "Thunderstorm")) {
            Picasso.get().load(drawable.violentstorm).into(iconWeather);
        } else {
            Picasso.get().load(drawable.severealert).into(iconWeather);
        }

    }

    private boolean responseGetMain(Response<WeatherRequest> response, String clouds) {
        return response.body().getWeathers()[0].getMain().equals(clouds);
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
}
