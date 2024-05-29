package com.example.weatherapp.Activities.Domains;

/**
 * Represents the weather data for a specific hour.
 */
public class Hourly implements Weather {
    private String hour;
    private double temp;
    private String picPath;

    /**
     * Constructs a new Hourly instance with the specified hour, temperature, and picture path.
     *
     * @param hour    the hour for which the weather data applies
     * @param temp    the temperature at the specified hour
     * @param picPath the path to the weather icon image
     */
    public Hourly(String hour, double temp, String picPath) {
        this.hour = hour;
        this.temp = temp;
        this.picPath = picPath;
    }

    /**
     * Gets the hour for which the weather data applies.
     *
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * Sets the hour for which the weather data applies.
     *
     * @param hour the hour to set
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * Gets the temperature at the specified hour.
     *
     * @return the temperature
     */
    public double getTemp() {
        return temp;
    }

    /**
     * Sets the temperature at the specified hour.
     *
     * @param temp the temperature to set
     */
    public void setTemp(double temp) {
        this.temp = temp;
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
}
