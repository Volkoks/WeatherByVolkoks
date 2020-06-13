package com.example.weatherbyvolkoks.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface EducationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCity(HistoryCity historyCity);

    @Delete
    void deleteCity(HistoryCity historyCity);
}
