package com.example.weatherbyvolkoks.presenter;

import com.example.weatherbyvolkoks.data.Constants;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.ListWeather;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.ILoaderWeather5Day;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeatehForecastFor5Day.LoaderWeather5day;

import retrofit2.Response;

public class PresenterForMainActivity implements ILoaderWeather5Day, IPresenterForMainAct.ForCitySelection {
    // - поля:
    private String base_city = Constants.BASE_CITY;
    private ListWeather[] listWeather;

    // - интерфейсы:
    private final IPresenterForMainAct.ForView IForView;

    public PresenterForMainActivity(IPresenterForMainAct.ForView iForView) {
        IForView = iForView;
    }


    private void initWeatherToAPI() {
        LoaderWeather5day loaderWeather5day = new LoaderWeather5day(this);
        loaderWeather5day.downloadWeather(base_city);
    }

    @Override
    public void weatherLoadFor5Day(Response<WeatherRequest5Day> response) {

    }

    @Override
    public void ADError(String title, String error) {

    }

    @Override
    public void getCity(String city) {
        base_city = city;
    }
}
