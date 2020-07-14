package com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {WeatherForecastFor5Day.class},version = 1)
public abstract class WeatherForecast5dayDatabase extends RoomDatabase {
    public abstract WeatherForecastDao getWeatherForecast();
}
