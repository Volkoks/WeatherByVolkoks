package com.example.weatherbyvolkoks;

import android.app.Application;

import androidx.room.Room;

import com.example.weatherbyvolkoks.data.Constants;
import com.example.weatherbyvolkoks.data.dataRoom.EducationDatabase;
import com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day.WeatherForecast5dayDatabase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    private static Retrofit retrofit;
    private static EducationDatabase db;
    private static WeatherForecast5dayDatabase db_WF_5day;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext()
                ,EducationDatabase.class
                ,"education_database")
                .allowMainThreadQueries() // Сделано для теста
                .build();
        db_WF_5day = Room.databaseBuilder(getApplicationContext()
        ,WeatherForecast5dayDatabase.class
        ,"weather_forecast_database")
                .allowMainThreadQueries()
                .build();

    }
    public static EducationDatabase getEducationDB(){
        return db;
    }
    public static WeatherForecast5dayDatabase getWeatherForecastDB(){
        return db_WF_5day;
    }

    public static Retrofit getCreateRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
