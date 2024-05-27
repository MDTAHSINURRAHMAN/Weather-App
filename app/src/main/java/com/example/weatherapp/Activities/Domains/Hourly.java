package com.example.weatherapp.Activities.Domains;

public class Hourly implements Weather {
    private String hour;
    private double temp;
    private String picPath;

    public Hourly(String hour, double temp, String picPath) {
        this.hour = hour;
        this.temp = temp;
        this.picPath = picPath;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
