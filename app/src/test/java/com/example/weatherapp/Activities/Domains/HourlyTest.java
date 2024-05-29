package com.example.weatherapp.Activities.Domains;

import junit.framework.TestCase;

/**
 * Test cases for the Hourly class to ensure its functionality.
 */
public class HourlyTest extends TestCase {

    /**
     * Test case for testing the getHour method.
     * It checks if the getHour method returns the correct hour.
     */
    public void testGetHour() {
        String expectedHour = "12:00";
        double temp = 25.5;
        String picPath = "path/to/image";

        Hourly hourly = new Hourly(expectedHour, temp, picPath);

        assertEquals(expectedHour, hourly.getHour());
    }

    /**
     * Test case for testing the setHour method.
     * It verifies if the setHour method sets the hour correctly.
     */
    public void testSetHour() {
        String expectedHour = "12:00";

        Hourly hourly = new Hourly("", 0, "");

        hourly.setHour(expectedHour);

        assertEquals(expectedHour, hourly.getHour());
    }

    /**
     * Test case for testing the getTemp method.
     * It checks if the getTemp method returns the correct temperature.
     */
    public void testGetTemp() {
        double expectedTemp = 25.5;
        String hour = "12:00";
        String picPath = "path/to/image";

        Hourly hourly = new Hourly(hour, expectedTemp, picPath);

        assertEquals(expectedTemp, hourly.getTemp());
    }

    /**
     * Test case for testing the setTemp method.
     * It verifies if the setTemp method sets the temperature correctly.
     */
    public void testSetTemp() {
        double expectedTemp = 25.5;

        Hourly hourly = new Hourly("", 0, "");

        hourly.setTemp(expectedTemp);

        assertEquals(expectedTemp, hourly.getTemp());
    }

    /**
     * Test case for testing the getPicPath method.
     * It checks if the getPicPath method returns the correct picture path.
     */
    public void testGetPicPath() {
        String expectedPicPath = "path/to/image";
        String hour = "12:00";
        double temp = 25.5;

        Hourly hourly = new Hourly(hour, temp, expectedPicPath);

        assertEquals(expectedPicPath, hourly.getPicPath());
    }

    /**
     * Test case for testing the setPicPath method.
     * It verifies if the setPicPath method sets the picture path correctly.
     */
    public void testSetPicPath() {
        String expectedPicPath = "path/to/image";

        Hourly hourly = new Hourly("", 0, "");

        hourly.setPicPath(expectedPicPath);

        assertEquals(expectedPicPath, hourly.getPicPath());
    }
}
