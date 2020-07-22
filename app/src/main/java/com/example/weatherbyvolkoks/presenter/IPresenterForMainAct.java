package com.example.weatherbyvolkoks.presenter;

import android.content.Context;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.ListWeather;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;
import com.example.weatherbyvolkoks.ui.WeatherForecastAdapter;

public interface IPresenterForMainAct {
    interface ForView {
        void setListView(WeatherRequest5Day request5Day);
    }

    interface ForPresenter {
        void weatherImageInit(WeatherRequest5Day weatherRequest5Day, ImageView iconWeather);
        void initAlertDialogAboutApp(Context context);
        WeatherForecastAdapter initAdapterAndRecyclerView(ListWeather[] listWeather);
    }

}
