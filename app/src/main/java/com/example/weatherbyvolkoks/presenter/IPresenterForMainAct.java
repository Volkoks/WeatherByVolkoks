package com.example.weatherbyvolkoks.presenter;

import android.widget.ImageView;

import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.ListWeather;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;

import retrofit2.Response;

public interface IPresenterForMainAct {
    interface ForView{
        void setListView(WeatherRequest5Day request5Day);
    }
    interface ForPresenter{
void weatherImageInit(WeatherRequest5Day weatherRequest5Day, ImageView iconWeather);
    }
    interface ForCitySelection{
        void getCity(String city);
    }
}
