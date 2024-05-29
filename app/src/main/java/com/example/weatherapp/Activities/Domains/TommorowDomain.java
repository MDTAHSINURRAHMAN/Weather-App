package com.example.weatherapp.Activities.Domains;

/**
 * Represents the weather forecast data for a specific day.
 */
public class TommorowDomain implements Weather {
    private String day;
    private String picPath;
    private String status;
    private int highTemp;
    private int lowTemp;

    /**
     * Constructs a new TommorowDomain instance with the specified day, picture path, status,
     * high temperature, and low temperature.
     *
     * @param day      the day for which the weather forecast applies
     * @param picPath  the path to the weather icon image
     * @param status   the weather status (e.g., sunny, cloudy)
     * @param highTemp the highest temperature forecasted for the day
     * @param lowTemp  the lowest temperature forecasted for the day
     */
    public TommorowDomain(String day, String picPath, String status, int highTemp, int lowTemp) {
        this.day = day;
        this.picPath = picPath;
        this.status = status;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }

    /**
     * Gets the day for which the weather forecast applies.
     *
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * Sets the day for which the weather forecast applies.
     *
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Gets the path to the weather icon image.
     *
     * @return the picture path
     */
    @Override
    public String getPicPath() {
        return picPath;
    }

    /**
     * Sets the path to the weather icon image.
     *
     * @param picPath the picture path to set
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    /**
     * Gets the weather status (e.g., sunny, cloudy).
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the weather status (e.g., sunny, cloudy).
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the highest temperature forecasted for the day.
     *
     * @return the high temperature
     */
    public int getHighTemp() {
        return highTemp;
    }

    /**
     * Sets the highest temperature forecasted for the day.
     *
     * @param highTemp the high temperature to set
     */
    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    /**
     * Gets the lowest temperature forecasted for the day.
     *
     * @return the low temperature
     */
    public int getLowTemp() {
        return lowTemp;
    }

    /**
     * Sets the lowest temperature forecasted for the day.
     *
     * @param lowTemp the low temperature to set
     */
    public void setLowTemp(int lowTemp) {
        this.lowTemp = lowTemp;
    }
}
