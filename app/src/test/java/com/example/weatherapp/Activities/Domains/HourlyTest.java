package com.example.weatherapp.Activities.Domains;

import junit.framework.TestCase;

public class HourlyTest extends TestCase {

    // Test case for testing the getHour method
    public void testGetHour() {
        String expectedHour = "12:00";
        double temp = 25.5;
        String picPath = "path/to/image";

        Hourly hourly = new Hourly(expectedHour, temp, picPath);

        assertEquals(expectedHour, hourly.getHour());
    }

    // Test case for testing the setHour method
    public void testSetHour() {
        String expectedHour = "12:00";

        Hourly hourly = new Hourly("", 0, "");

        hourly.setHour(expectedHour);

        assertEquals(expectedHour, hourly.getHour());
    }

    // Test case for testing the getTemp method
    public void testGetTemp() {
        double expectedTemp = 25.5;
        String hour = "12:00";
        String picPath = "path/to/image";

        Hourly hourly = new Hourly(hour, expectedTemp, picPath);

        assertEquals(expectedTemp, hourly.getTemp());
    }

    // Test case for testing the setTemp method
    public void testSetTemp() {
        double expectedTemp = 25.5;

        Hourly hourly = new Hourly("", 0, "");

        hourly.setTemp(expectedTemp);

        assertEquals(expectedTemp, hourly.getTemp());
    }

    // Test case for testing the getPicPath method
    public void testGetPicPath() {
        String expectedPicPath = "path/to/image";
        String hour = "12:00";
        double temp = 25.5;

        Hourly hourly = new Hourly(hour, temp, expectedPicPath);

        assertEquals(expectedPicPath, hourly.getPicPath());
    }

    // Test case for testing the setPicPath method
    public void testSetPicPath() {
        String expectedPicPath = "path/to/image";

        Hourly hourly = new Hourly("", 0, "");

        hourly.setPicPath(expectedPicPath);

        assertEquals(expectedPicPath, hourly.getPicPath());
    }
}
