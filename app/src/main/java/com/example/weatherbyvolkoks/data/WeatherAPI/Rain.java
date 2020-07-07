package com.example.weatherbyvolkoks.data.WeatherAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("1h")
    @Expose
    private float wind_per_hour;

    public float getWind_per_hour() {
        return wind_per_hour;
    }

    public void setWind_per_hour(float wind_per_hour) {
        this.wind_per_hour = wind_per_hour;
    }
}
