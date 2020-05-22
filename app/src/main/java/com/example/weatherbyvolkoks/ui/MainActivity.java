package com.example.weatherbyvolkoks.ui;

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
import com.example.weatherbyvolkoks.BaseActivity;
import com.example.weatherbyvolkoks.data.API.Weather;
import com.example.weatherbyvolkoks.data.LoadWeather;
import com.example.weatherbyvolkoks.data.InterfaceLoaderWeather;
import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.Soc.SocSourceBuilder;
import com.example.weatherbyvolkoks.data.Soc.SocialDataSource;
import com.example.weatherbyvolkoks.data.API.WeatherRequest;

import static com.example.weatherbyvolkoks.R.*;

public class MainActivity extends BaseActivity implements InterfaceLoaderWeather {
    private static String citys = "Moscow";
    private final static int REQUEST_CODE = 1;
    private final static int SETTING_CODE = 2;

    private TextView city;
    private TextView temperature;
    private TextView description;
    private ImageView iconWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        SocialDataSource sourceData = new SocSourceBuilder().setResources(getResources()).build();
        init();
        initRecyclerView(sourceData);
    }

    private void init() {
        city = findViewById(id.City);
        temperature = findViewById(id.Temperature);
        description = findViewById(id.weather_description);
        iconWeather = findViewById(id.iconWeatherView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void initWeatherToAPI() {
        LoadWeather loadWeather = new LoadWeather(this);
        loadWeather.loadWeather(citys);
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
                city.setText(parcel.cityName);
                citys = parcel.weatherCityName;
                initWeatherToAPI();

            }
        }
    }

    @Override
    public void activate(WeatherRequest weatherRequest) {
        city.setText(weatherRequest.getName());
        temperature.setText(String.format(String.valueOf(weatherRequest.getMain().getTemp())));
        description.setText(weatherRequest.getWeathers()[0].getDescription());
        if (weatherRequest.getWeathers()[0].getMain().equals("Clouds")){
            iconWeather.setImageDrawable(getDrawable(drawable.sun));
        }else{
            iconWeather.setImageDrawable(getDrawable(drawable.rain));
        }

    }

}
