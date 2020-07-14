package com.example.weatherbyvolkoks.data.loaderWeather.loaderWeather1day;

import com.example.weatherbyvolkoks.data.WeatherAPI_1day.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoaderWeatherRetrofit {
   @GET("weather")
    Call<WeatherRequest> loadWeather(@Query("q") String city,@Query("units") String units,@Query("lang") String language,@Query("appid") String keyApi);
}
