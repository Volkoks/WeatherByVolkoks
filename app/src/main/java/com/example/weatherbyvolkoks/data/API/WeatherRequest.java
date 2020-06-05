package com.example.weatherbyvolkoks.data.API;

public class WeatherRequest {
    private Weather[] weather;
    private Main main;
    private String name;
    private float timezone;

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
}
