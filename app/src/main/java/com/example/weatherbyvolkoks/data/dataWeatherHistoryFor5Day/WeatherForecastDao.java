package com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface WeatherForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWeatherHistory(WeatherForecastFor5Day weatherForecastFor5Day);

    @Delete
    void deleteWeatherHistory(WeatherForecastFor5Day weatherForecastFor5Day);

    @Query("SELECT COUNT() FROM weatherForecastFor5Day")
    List<WeatherForecastFor5Day> getAllWeatherForecast();

}
