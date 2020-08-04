package com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day;

import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;

import retrofit2.Response;

public interface ILoaderWeather5Day {
    interface Loader{
        void  weatherLoadFor5Day (Response<WeatherRequest5Day> response);
    }
    interface Error{
        void ADError(String title, String error);
    }

}
