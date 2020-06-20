package com.example.weatherbyvolkoks.data.dataRoom;

import java.util.List;

public class WeatherSource {
    private WeatherDao weatherDao;
    private List<HistoryCity> historyCities;

    public WeatherSource(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    public void loadHistoryCity() {
        historyCities = weatherDao.getAllCity();
    }

    public List<HistoryCity> getHistoryCities() {
        if (historyCities == null) {
            loadHistoryCity();
        }
        return historyCities;
    }

    public void addCity(HistoryCity historyCity) {
        weatherDao.addCity(historyCity);
        loadHistoryCity();
    }

    public void deleteCity(HistoryCity historyCity) {
        weatherDao.deleteCity(historyCity);
        loadHistoryCity();
    }

    public long getCountCity() {
        return weatherDao.getCountHistoryCity();
    }

    public void getCityByName(String cityName) {
        weatherDao.getCityByName(cityName);
        loadHistoryCity();
    }
}
