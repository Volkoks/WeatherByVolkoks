package com.example.weatherbyvolkoks.data.loaderWeather;

import com.example.weatherbyvolkoks.data.API.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoaderWeatherRetrofit {
   @GET("weather")
    Call<WeatherRequest> loadWeather(@Query("q") String city,@Query("units") String units,@Query("lang") String language,@Query("appid") String keyApi);
}
