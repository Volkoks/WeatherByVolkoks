package com.example.weatherbyvolkoks.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"city_name"})})
public class HistoryCity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "city_name")
    public String cityName;

    @ColumnInfo(name = "temperature")
    public int temperature;

    @ColumnInfo(name = "description")
    public String description;
}
