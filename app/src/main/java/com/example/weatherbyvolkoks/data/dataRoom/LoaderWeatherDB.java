package com.example.weatherbyvolkoks.data.dataRoom;

import com.example.weatherbyvolkoks.MyApp;
import com.example.weatherbyvolkoks.data.API.WeatherRequest;
import com.example.weatherbyvolkoks.data.loaderWeather.ILoaderWeather;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeather;

import java.util.List;

import retrofit2.Response;

public class LoaderWeatherDB implements ILoaderWeather {
    private String cityNameDB;
    private String description;
    private int temperature;
    private WeatherDao weatherDao;
    private List<HistoryCity> historyCities;
    private HistoryCity historyCity;


    public void initWeatherToAPI(String citys) {
        LoaderWeather loaderWeather = new LoaderWeather(this);
        loaderWeather.downloadWeather(citys);
    }

    @Override
    public void activate(Response<WeatherRequest> response) {
        cityNameDB = response.body().getName();
        description = response.body().getWeathers()[0].getDescription();
        temperature = (int) response.body().getMain().getTemp();
    }

    @Override
    public void ADError(String title, String error) {

    }

    public HistoryCity historyCity(){
        historyCity.temperature = temperature;
        historyCity.description = description;
        historyCity.cityName = cityNameDB;
        return historyCity;
    }


}
