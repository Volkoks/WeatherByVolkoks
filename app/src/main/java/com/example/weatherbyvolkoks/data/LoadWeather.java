package com.example.weatherbyvolkoks.data;

import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.weatherbyvolkoks.BuildConfig;
import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.API.WeatherRequest;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class LoadWeather {

    private InterfaceLoaderWeather interfaceLoaderWeather;

    public LoadWeather(InterfaceLoaderWeather interfaceLoaderWeather) {
        this.interfaceLoaderWeather = interfaceLoaderWeather;
    }

    public void loadWeather(String city) {
        try {
            final URL uri = new URL(Constants.WEATHER_URL_START + city + Constants.WEATHER_URL_FINISH + BuildConfig.WEATHER_API_KEY);
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
                                interfaceLoaderWeather.activate(weatherRequest);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                private String getLines(BufferedReader in) {
                    return in.lines().collect(Collectors.joining("\n"));
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void displayWeather(View view, WeatherRequest weatherRequest) {
        TextView city = view.findViewById(R.id.City);
        TextView temperature = view.findViewById(R.id.Temperature);
        TextView description = view.findViewById(R.id.weather_description);
        city.setText(weatherRequest.getName());
        temperature.setText(String.format(String.valueOf(weatherRequest.getMain().getTemp())));
        description.setText(weatherRequest.getWeathers()[0].getDescription());
    }
}
