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
}
