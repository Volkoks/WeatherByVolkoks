package com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day;

import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoaderWeatherRetrofitFor5Day {
    @GET("forecast")
    Call<WeatherRequest5Day> loadWeather(@Query("q") String city, @Query("units") String units, @Query("lang") String language, @Query("appid") String keyApi);
}
