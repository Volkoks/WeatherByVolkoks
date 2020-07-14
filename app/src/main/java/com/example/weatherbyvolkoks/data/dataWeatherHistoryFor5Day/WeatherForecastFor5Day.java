package com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(indices = {@Index(value = "date")})
public class WeatherForecastFor5Day {

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "temperature")
    public int temperature;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "main")
    public String main;
}
