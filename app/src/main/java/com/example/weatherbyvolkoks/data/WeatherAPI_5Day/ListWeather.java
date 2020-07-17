package com.example.weatherbyvolkoks.data.WeatherAPI_5Day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListWeather {
    @SerializedName("dt")
    @Expose
    private float dt;
    @SerializedName("main")
    @Expose
    Main main;
    @SerializedName("weather")
    @Expose
    Weather[] weather;
    @SerializedName("clouds")
    @Expose
    Clouds clouds;
    @SerializedName("wind")
    @Expose
    Wind wind;
    @SerializedName("rain")
    @Expose
    Rain rain;
    @SerializedName("sys")
    @Expose
    Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dt_txt;

    public float getDt() {
        return dt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
