package com.example.weatherbyvolkoks.data.WeatherAPI_5Day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherRequest {
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
    ArrayList<ListWeather> weatherArrayList = new ArrayList<ListWeather>();
    @SerializedName("city")
    @Expose
    City city;
}
