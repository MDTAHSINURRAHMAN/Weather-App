package com.example.weatherapp.Activities.Domains;

public class TommorowDomain {
    private String day;
    private String picPath;
    private String status;
    private int highTemp;
    private int lowTemp;

    public TommorowDomain(String day, String picPath, String status, int highTemp, int lowTemp) {
        this.day = day;
        this.picPath = picPath;
        this.status = status;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }

    public String getDay() {
        return day;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getStatus() {
        return status;
    }

    public int getHighTemp() {
        return highTemp;
    }

    public int getLowTemp() {
        return lowTemp;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    public void setLowTemp(int lowTemp) {
        this.lowTemp = lowTemp;
    }
}
