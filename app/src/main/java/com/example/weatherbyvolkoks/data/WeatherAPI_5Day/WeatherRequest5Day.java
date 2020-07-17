package com.example.weatherbyvolkoks.data.WeatherAPI_5Day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherRequest5Day {
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private float message;
    @SerializedName("cnt")
    @Expose
    private float cnt;
    @SerializedName("list")
    @Expose
   ListWeather[] listWeathers;

    public ListWeather[] getListWeathers() {
        return listWeathers;
    }

    public void setListWeathers(ListWeather[] listWeathers) {
        this.listWeathers = listWeathers;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public float getCnt() {
        return cnt;
    }

    public void setCnt(float cnt) {
        this.cnt = cnt;
    }



    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @SerializedName("city")
    @Expose
    City city;
}
