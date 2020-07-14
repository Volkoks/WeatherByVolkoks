package com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day;

import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;

import retrofit2.Response;

public interface ILoaderWeather5Day {
    void  weatherLoadFor5Day (Response<WeatherRequest5Day> response);
    void ADError(String title, String error);
}
