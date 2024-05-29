package com.example.weatherapp.Activities.Domains;

import junit.framework.TestCase;

/**
 * This class represents a domain object for tomorrow's weather forecast.
 * It encapsulates information such as the day, weather status, temperature, and picture path.
 */
public class TommorowDomainTest extends TestCase {

    /**
     * Test case for testing the getDay method
     */
    public void testGetDay() {
        // Test data
        String expectedDay = "Monday";
        String picPath = "path/to/image";
        String status = "sun";
        int highTemp = 25;
        int lowTemp = 18;

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain(expectedDay, picPath, status, highTemp, lowTemp);

        // Assert
        assertEquals(expectedDay, tommorowDomain.getDay());
    }

    /**
     * Test case for testing the getPicPath method
     */
    public void testGetPicPath() {
        // Test data
        String day = "Monday";
        String expectedPicPath = "path/to/image";
        String status = "sun";
        int highTemp = 25;
        int lowTemp = 18;

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain(day, expectedPicPath, status, highTemp, lowTemp);

        // Assert
        assertEquals(expectedPicPath, tommorowDomain.getPicPath());
    }

    /**
     * Test case for testing the getStatus method
     */
    public void testGetStatus() {
        // Test data
        String day = "Monday";
        String picPath = "path/to/image";
        String expectedStatus = "sun";
        int highTemp = 25;
        int lowTemp = 18;

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain(day, picPath, expectedStatus, highTemp, lowTemp);

        // Assert
        assertEquals(expectedStatus, tommorowDomain.getStatus());
    }

    /**
     * Test case for testing the getHighTemp method
     */
    public void testGetHighTemp() {
        // Test data
        String day = "Monday";
        String picPath = "path/to/image";
        String status = "sun";
        int expectedHighTemp = 25;
        int lowTemp = 18;

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain(day, picPath, status, expectedHighTemp, lowTemp);

        // Assert
        assertEquals(expectedHighTemp, tommorowDomain.getHighTemp());
    }

    /**
     * Test case for testing the getLowTemp method
     */
    public void testGetLowTemp() {
        // Test data
        String day = "Monday";
        String picPath = "path/to/image";
        String status = "sun";
        int highTemp = 25;
        int expectedLowTemp = 18;

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain(day, picPath, status, highTemp, expectedLowTemp);

        // Assert
        assertEquals(expectedLowTemp, tommorowDomain.getLowTemp());
    }

    /**
     * Test case for testing the setDay method
     */
    public void testSetDay() {
        // Test data
        String expectedDay = "Monday";

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        // Set day
        tommorowDomain.setDay(expectedDay);

        // Assert
        assertEquals(expectedDay, tommorowDomain.getDay());
    }

    /**
     * Test case for testing the setPicPath method
     */
    public void testSetPicPath() {
        // Test data
        String expectedPicPath = "path/to/image";

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        // Set pic path
        tommorowDomain.setPicPath(expectedPicPath);

        // Assert
        assertEquals(expectedPicPath, tommorowDomain.getPicPath());
    }

    /**
     * Test case for testing the setStatus method
     */
    public void testSetStatus() {
        // Test data
        String expectedStatus = "sun";

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        // Set status
        tommorowDomain.setStatus(expectedStatus);

        // Assert
        assertEquals(expectedStatus, tommorowDomain.getStatus());
    }

    /**
     * Test case for testing the setHighTemp method
     */
    public void testSetHighTemp() {
        // Test data
        int expectedHighTemp = 25;

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        // Set high temp
        tommorowDomain.setHighTemp(expectedHighTemp);

        // Assert
        assertEquals(expectedHighTemp, tommorowDomain.getHighTemp());
    }

    /**
     * Test case for testing the setLowTemp method
     */
    public void testSetLowTemp() {
        // Test data
        int expectedLowTemp = 18;

        // Create instance of TommorowDomain
        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        // Set low temp
        tommorowDomain.setLowTemp(expectedLowTemp);

        // Assert
        assertEquals(expectedLowTemp, tommorowDomain.getLowTemp());
    }
}
