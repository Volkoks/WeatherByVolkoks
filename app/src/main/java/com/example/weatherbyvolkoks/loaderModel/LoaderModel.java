package com.example.weatherbyvolkoks.loaderModel;

import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.ILoaderWeather5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.LoaderWeather5day;

import retrofit2.Response;

public class LoaderModel implements ILoaderWeather5Day.Loader, ILoadModel.ForILoadModel{
    private WeatherRequest5Day weatherRequest5Day;
    private LoaderWeather5day loaderWeather5day;
    private ILoadModel.ForPresenter loadModel;

    public LoaderModel(String city, ILoadModel.ForPresenter loadModel) {
        this.loadModel = loadModel;
        loadWeather(city);
    }

    private void loadWeather(String city) {
        loaderWeather5day = new LoaderWeather5day(this);
        loaderWeather5day.downloadWeather(city);
    }

    @Override
    public void weatherLoadFor5Day(Response<WeatherRequest5Day> response) {
        weatherRequest5Day = response.body();
        loadModel.loadWeather(weatherRequest5Day);
    }

    @Override
    public void initLoadWeather(String city) {
        loadWeather(city);
    }
}
