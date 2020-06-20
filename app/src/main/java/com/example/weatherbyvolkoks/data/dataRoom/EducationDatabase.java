package com.example.weatherbyvolkoks.data.dataRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {HistoryCity.class}, version = 1)
public abstract class EducationDatabase extends RoomDatabase {
    public abstract WeatherDao getEducationDao();
}
