package com.example.weatherbyvolkoks.data.loaderWeather.loaderWeather1day;

import com.example.weatherbyvolkoks.data.WeatherAPI_1day.WeatherRequest;

import retrofit2.Response;

public interface ILoaderWeather {
    void activate(Response<WeatherRequest> response);
    void ADError(String title, String error);
}
