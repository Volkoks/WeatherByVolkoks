package com.example.weatherbyvolkoks.data;

import com.example.weatherbyvolkoks.data.API.WeatherRequest;

import retrofit2.Response;

public interface ILoaderWeather {
    void activate(Response<WeatherRequest> response);
    void ADError(String title, String error);
}
