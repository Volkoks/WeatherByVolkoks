package com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = "date")})
public class WeatherForecastFor5Day {
    @PrimaryKey
    @ColumnInfo(name = "date")
    @NonNull
    public String date;

    @ColumnInfo(name = "temperature")
    public int temperature;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "main")
    public String main;
}
