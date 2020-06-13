package com.example.weatherbyvolkoks;

import android.app.Application;

import androidx.room.Room;

import com.example.weatherbyvolkoks.data.Constants;
import com.example.weatherbyvolkoks.data.EducationDao;
import com.example.weatherbyvolkoks.data.EducationDatabase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    private static Retrofit retrofit;
    private static MyApp instance;
    private EducationDatabase db;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(getApplicationContext()
                ,EducationDatabase.class
                ,"education_database")
                .allowMainThreadQueries()
                .build();
    }
    public EducationDao getEducationDao(){
        return db.getEducationDao();
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
