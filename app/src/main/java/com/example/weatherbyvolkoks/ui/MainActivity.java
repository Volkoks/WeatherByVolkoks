package com.example.weatherbyvolkoks.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.weatherbyvolkoks.BaseActivity;
import com.example.weatherbyvolkoks.BuildConfig;
import com.example.weatherbyvolkoks.Parcel;
import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.SocSourceBuilder;
import com.example.weatherbyvolkoks.data.SocialDataSource;
import com.example.weatherbyvolkoks.data.WeatherRequest;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import static com.example.weatherbyvolkoks.R.*;

public class MainActivity extends BaseActivity {

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=moscow&units=metric&lang=ru&appid=";
    private final static int REQUEST_CODE = 1;
    private final static int SETTING_CODE = 2;
    private static final String TAG = "WEATHER_MY";

    private TextView city;
    private TextView temperature;
    private TextView description;


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
                refreshWeather();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshWeather() {
        try {
            final URL uri = new URL(WEATHER_URL + BuildConfig.WEATHER_API_KEY);
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String result = getLines(in);
                        Gson gson = new Gson();
                        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                displayWeather(weatherRequest);
                            }
                        });
                    } catch (Exception e) {
                        Snackbar.make(city, "Ошибка соеденения", Snackbar.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                private String getLines(BufferedReader in) {
                    return in.lines().collect(Collectors.joining("\n"));
                }
            }).start();
        } catch (Exception e) {
            Snackbar.make(city, "Неверный URL", Snackbar.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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
            }
        }
    }
    private void displayWeather(WeatherRequest weatherRequest){
        city.setText(weatherRequest.getName());
        temperature.setText(String.format(String.valueOf(weatherRequest.getMain().getTemp())));
        description.setText(weatherRequest.getWeathers()[0].getDescription());

    }
}
