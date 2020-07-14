package com.example.weatherbyvolkoks.data.loaderWeather;

import com.example.weatherbyvolkoks.BuildConfig;
import com.example.weatherbyvolkoks.MyApp;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoaderWeather5day {
    private LoaderWeatherRetrofitFor5Day loaderWeatherRetrofitFor5Day;
    private ILoaderWeather5Day iLoaderWeather5Day;

    public LoaderWeather5day(ILoaderWeather5Day iLoaderWeather5Day) {
        this.iLoaderWeather5Day = iLoaderWeather5Day;
    }

    public void downloadWeather(String cityName) {
        initRetrofit();
        requestRetrofit(cityName);
    }

    private void initRetrofit() {
        Retrofit retrofit = MyApp.getCreateRetrofit();
        loaderWeatherRetrofitFor5Day = retrofit.create(LoaderWeatherRetrofitFor5Day.class);
    }

    private void requestRetrofit(String cityName) {
        loaderWeatherRetrofitFor5Day.loadWeather(cityName, "metric", "ru", BuildConfig.WEATHER_API_KEY)
                .enqueue(new Callback<WeatherRequest5Day>() {
                    @Override
                    public void onResponse(Call<WeatherRequest5Day> call, Response<WeatherRequest5Day> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            iLoaderWeather5Day.weatherLoadFor5Day(response);
                        }
                        if (!response.isSuccessful() && response.errorBody() != null) {
                            try {
                                JSONObject jsonError = new JSONObject(response.errorBody().string());
                                String error = jsonError.getString("message");
                                iLoaderWeather5Day.ADError("Ошибка JSON 5 day", error);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                iLoaderWeather5Day.ADError("Ошибка JSON JSONException 5 day", e.getMessage());
                            } catch (IOException e) {
                                e.printStackTrace();
                                iLoaderWeather5Day.ADError("Ошибка JSON IOException 5 day", e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest5Day> call, Throwable t) {
                            iLoaderWeather5Day.ADError("ERROR onFailure 5 day", t.getMessage());
                    }
                });

    }
}

