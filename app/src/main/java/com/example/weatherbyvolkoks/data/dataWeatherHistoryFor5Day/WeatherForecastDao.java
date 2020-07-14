package com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;


@Dao
public interface WeatherForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWeatherHistory(WeatherForecastFor5Day weatherForecastFor5Day);

    @Delete
    void deleteWeatherHistory(WeatherForecastFor5Day weatherForecastFor5Day);

}
