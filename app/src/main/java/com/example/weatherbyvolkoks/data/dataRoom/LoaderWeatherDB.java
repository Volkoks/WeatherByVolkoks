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

    public LoaderWeatherDB(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    private void initWeatherToAPI(String citys) {
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

    private HistoryCity addNewHhistoryCity() {
        HistoryCity historyCity = new HistoryCity();
        historyCity.temperature = temperature;
        historyCity.description = description;
        historyCity.cityName = cityNameDB;
        return historyCity;
    }

    public long getCountCity() {
        return weatherDao.getCountHistoryCity();
    }

    public List<HistoryCity> getHistoryCities() {
        if (historyCities == null) {
            loadHistoryCity();
        }
        return historyCities;
    }

    public void loadHistoryCity() {
        historyCities = weatherDao.getAllCity();
    }

    public void addCity(String city, HistoryCity historyCity) {
        initWeatherToAPI(city);
        addNewHhistoryCity();
        weatherDao.addCity(historyCity);
        loadHistoryCity();
    }

}
