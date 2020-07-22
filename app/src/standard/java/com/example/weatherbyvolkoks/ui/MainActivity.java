package com.example.weatherbyvolkoks.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.weatherbyvolkoks.BaseActivity;

import com.example.weatherbyvolkoks.GetCityes;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.ListWeather;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;


import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;

import com.example.weatherbyvolkoks.presenter.IPresenterForMainAct;
import com.example.weatherbyvolkoks.presenter.PresenterForMainActivity;

import java.util.Objects;

import static com.example.weatherbyvolkoks.R.*;
import static java.lang.String.format;

public class MainActivity extends BaseActivity implements GetCityes, IPresenterForMainAct.ForView {
    private static String mainCity = "Moscow";

    private final static int REQUEST_CODE = 1;
    private final static int SETTING_CODE = 2;

    private PresenterForMainActivity presenter;

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
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        initGUI();
        presenter = new PresenterForMainActivity(mainCity, this);

        testVisibleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibilityOfFAdvancedOptions();
            }
        });

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
        recyclerView = findViewById(id.recyclerView);


    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void setListView(WeatherRequest5Day request5Day) {
        int valueTempMax = (int) request5Day.getListWeathers()[0].getMain().getTemp_max();
        int valueTempMin = (int) request5Day.getListWeathers()[0].getMain().getTemp_min();

        city.setText(request5Day.getCity().getName());
        temperature.setText(((int) request5Day.getListWeathers()[0].getMain().getTemp()) + "\u2103");
        temp_max_min.setText(format("%d/%d" + "\u2103", valueTempMax, valueTempMin));
        description.setText(request5Day.getListWeathers()[0].getWeather()[0].getDescription());
        humidity.setText(((int) request5Day.getListWeathers()[0].getMain().getHumidity()) + "%");
        wind.setText(((int) request5Day.getListWeathers()[0].getWind().getSpeed()) + "m/s");
        pressure.setText(request5Day.getListWeathers()[0].getMain().getPressure() + "hPa");

        presenter.weatherImageInit(request5Day, iconWeather);
        initRecyclerView(request5Day.getListWeathers());

    }

    private void initRecyclerView(ListWeather[] listWeathers) {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(getDrawable(drawable.litle_separator)));
WeatherForecastAdapter adapter = new WeatherForecastAdapter(listWeathers);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
                presenter = new PresenterForMainActivity(mainCity, this);
                break;
            case R.id.about_app:
                presenter.initAlertDialogAboutApp(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void activityTransitionIntent(Class youClass, int RequestCode) {
        Intent intent = new Intent(getApplicationContext(), youClass);
        startActivityForResult(intent, RequestCode);
        return;
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
                mainCity = parcel.weatherCityName;
                presenter = new PresenterForMainActivity(mainCity, this);
            }
        }
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
