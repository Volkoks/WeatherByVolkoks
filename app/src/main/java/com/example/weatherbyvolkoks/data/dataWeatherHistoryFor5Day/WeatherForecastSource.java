package com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day;

import java.util.List;

public class WeatherForecastSource {
    private WeatherForecastDao weatherForecastDao;
    private List<WeatherForecastFor5Day> weatherForecastFor5DayList;

    public WeatherForecastSource(WeatherForecastDao weatherForecastDao) {
        this.weatherForecastDao = weatherForecastDao;
    }
    public void loadWeatherForecast(){
        weatherForecastFor5DayList = weatherForecastDao.getAllWeatherForecast();
    }
    public void addWeatherForecast(WeatherForecastFor5Day weatherForecastFor5Day){
        weatherForecastDao.addWeatherHistory(weatherForecastFor5Day);
        loadWeatherForecast();
    }
    public void deleteWeatherForecast(WeatherForecastFor5Day weatherForecastFor5Day){
        weatherForecastDao.deleteWeatherHistory(weatherForecastFor5Day);
        loadWeatherForecast();
    }
    public List<WeatherForecastFor5Day> getWeatherForecastFor5DayList(){
        if (weatherForecastFor5DayList == null){
            loadWeatherForecast();
        }
        return weatherForecastFor5DayList;
    }
}
