package com.example.weatherbyvolkoks.data.WeatherAPI_5Day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    @Expose
    private float all;

    public float getAll() {
        return all;
    }

    public void setAll(float all) {
        this.all = all;
    }
}
