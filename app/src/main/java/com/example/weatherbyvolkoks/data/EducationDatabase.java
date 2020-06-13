package com.example.weatherbyvolkoks.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {HistoryCity.class}, version = 1)
public abstract class EducationDatabase extends RoomDatabase {
    public abstract EducationDao getEducationDao();
}
