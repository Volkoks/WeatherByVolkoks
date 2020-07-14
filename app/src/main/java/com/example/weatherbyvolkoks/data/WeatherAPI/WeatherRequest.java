package com.example.weatherbyvolkoks.data.WeatherAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherRequest {
    @SerializedName("weather")
    @Expose
    private Weather[] weather;
    @SerializedName("main")
    private Main main;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    @SerializedName("sys")
    @Expose
    private Sys sys;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("timezone")
    @Expose
    private float timezone;
    @SerializedName("visibility")
    @Expose
    private float visibility;
    @SerializedName("dt")
    @Expose
    private float dt;
    @SerializedName("id")
   @Expose
    private float id;
    @SerializedName("cod")
    @Expose
    private float cod;
    public Weather[] getWeathers() {
        return weather;
    }

    public void setWeathers(Weather[] weathers) {
        this.weather = weathers;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTimezone() {
        return timezone;
    }

    public void setTimezone(float timezone) {
        this.timezone = timezone;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
