package com.example.weatherbyvolkoks.data.WeatherAPI_5Day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("id")
    @Expose
    private float id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private float population;
    @SerializedName("timezone")
    @Expose
    private float timezone;
    @SerializedName("sunrise")
    @Expose
    private float sunrise;
    @SerializedName("sunset")
    @Expose
    private float sunset;


    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getPopulation() {
        return population;
    }

    public void setPopulation(float population) {
        this.population = population;
    }

    public float getTimezone() {
        return timezone;
    }

    public void setTimezone(float timezone) {
        this.timezone = timezone;
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
