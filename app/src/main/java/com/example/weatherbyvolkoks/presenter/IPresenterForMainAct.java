package com.example.weatherbyvolkoks.presenter;

public interface IPresenterForMainAct {
    interface ForView{

    }
    interface ForPresenter{

    }
    interface ForCitySelection{
        void getCity(String city);
    }
}
