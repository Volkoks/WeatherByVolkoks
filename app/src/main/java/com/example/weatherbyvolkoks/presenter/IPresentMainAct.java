package com.example.weatherbyvolkoks.presenter;

import android.widget.ImageView;

import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;

public interface IPresentMainAct {
    interface ForView {
        void setListView(WeatherRequest5Day request5Day);
    }

    interface ForPresenter {
        void updateWeather();
        public void weatherImageInit(WeatherRequest5Day request5Day, ImageView iconWeather);
    }
}
