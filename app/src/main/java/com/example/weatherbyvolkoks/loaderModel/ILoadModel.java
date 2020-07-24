package com.example.weatherbyvolkoks.loaderModel;

import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;

public interface ILoadModel {
    interface ForPresenter{
        void loadWeather(WeatherRequest5Day request5Day);
    }
    interface ForILoadModel{
        void initLoadWeather(String city);
    }
}
