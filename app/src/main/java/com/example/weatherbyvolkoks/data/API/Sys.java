package com.example.weatherbyvolkoks.data.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("type")
    @Expose
    private float type;
    @SerializedName("id")
    @Expose
    private float id;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private float sunrise;
    @SerializedName("sunset")
    @Expose
    private float sunset;

    public float getType() {
        return type;
    }

    public void setType(float type) {
        this.type = type;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getSunrise() {
        return sunrise;
    }

    public void setSunrise(float sunrise) {
        this.sunrise = sunrise;
    }

    public float getSunset() {
        return sunset;
    }

    public void setSunset(float sunset) {
        this.sunset = sunset;
    }
}
