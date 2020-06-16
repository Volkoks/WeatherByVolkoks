package com.example.weatherbyvolkoks.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EducationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCity(HistoryCity historyCity);

    @Delete
    void deleteCity(HistoryCity historyCity);

    @Query("SELECT * FROM historyCity")
    List<HistoryCity> getAllCity();

    @Query("SELECT COUNT() FROM historyCity")
    long getCountHistoryCity();

    @Query("SELECT * FROM historyCity WHERE city_name = :cityName")
    List<HistoryCity> getCityByName(String cityName);

}
