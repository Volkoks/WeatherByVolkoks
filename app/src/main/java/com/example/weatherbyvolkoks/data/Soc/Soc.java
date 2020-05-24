package com.example.weatherbyvolkoks.data.Soc;

public class Soc {

    private String dayOfWeek; // день недели
    private String dateAndMonth; // дата
    private int iconWeather; // изображение

    public Soc(String dayOfWeek, String dateAndMonth, int iconWeather) {
        this.dayOfWeek = dayOfWeek;
        this.dateAndMonth = dateAndMonth;
        this.iconWeather = iconWeather;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getDateAndMonth() {
        return dateAndMonth;
    }

    public int getIconWeather() {
        return iconWeather;
    }
}
