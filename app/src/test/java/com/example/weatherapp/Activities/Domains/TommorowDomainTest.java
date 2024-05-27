package com.example.weatherapp.Activities.Domains;

import junit.framework.TestCase;

public class TommorowDomainTest extends TestCase {

    // Test case for testing the getDay method
    public void testGetDay() {
        String expectedDay = "Monday";
        String picPath = "path/to/image";
        String status = "sun";
        int highTemp = 25;
        int lowTemp = 18;

        TommorowDomain tommorowDomain = new TommorowDomain(expectedDay, picPath, status, highTemp, lowTemp);

        assertEquals(expectedDay, tommorowDomain.getDay());
    }

    // Test case for testing the getPicPath method
    public void testGetPicPath() {
        String day = "Monday";
        String expectedPicPath = "path/to/image";
        String status = "sun";
        int highTemp = 25;
        int lowTemp = 18;

        TommorowDomain tommorowDomain = new TommorowDomain(day, expectedPicPath, status, highTemp, lowTemp);

        assertEquals(expectedPicPath, tommorowDomain.getPicPath());
    }

    // Test case for testing the getStatus method
    public void testGetStatus() {
        String day = "Monday";
        String picPath = "path/to/image";
        String expectedStatus = "sun";
        int highTemp = 25;
        int lowTemp = 18;

        TommorowDomain tommorowDomain = new TommorowDomain(day, picPath, expectedStatus, highTemp, lowTemp);

        assertEquals(expectedStatus, tommorowDomain.getStatus());
    }

    // Test case for testing the getHighTemp method
    public void testGetHighTemp() {
        String day = "Monday";
        String picPath = "path/to/image";
        String status = "sun";
        int expectedHighTemp = 25;
        int lowTemp = 18;

        TommorowDomain tommorowDomain = new TommorowDomain(day, picPath, status, expectedHighTemp, lowTemp);

        assertEquals(expectedHighTemp, tommorowDomain.getHighTemp());
    }

    // Test case for testing the getLowTemp method
    public void testGetLowTemp() {
        String day = "Monday";
        String picPath = "path/to/image";
        String status = "sun";
        int highTemp = 25;
        int expectedLowTemp = 18;

        TommorowDomain tommorowDomain = new TommorowDomain(day, picPath, status, highTemp, expectedLowTemp);

        assertEquals(expectedLowTemp, tommorowDomain.getLowTemp());
    }

    // Test case for testing the setDay method
    public void testSetDay() {
        String expectedDay = "Monday";

        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        tommorowDomain.setDay(expectedDay);

        assertEquals(expectedDay, tommorowDomain.getDay());
    }

    // Test case for testing the setPicPath method
    public void testSetPicPath() {
        String expectedPicPath = "path/to/image";

        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        tommorowDomain.setPicPath(expectedPicPath);

        assertEquals(expectedPicPath, tommorowDomain.getPicPath());
    }

    // Test case for testing the setStatus method
    public void testSetStatus() {
        String expectedStatus = "sun";

        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        tommorowDomain.setStatus(expectedStatus);

        assertEquals(expectedStatus, tommorowDomain.getStatus());
    }

    // Test case for testing the setHighTemp method
    public void testSetHighTemp() {
        int expectedHighTemp = 25;

        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        tommorowDomain.setHighTemp(expectedHighTemp);

        assertEquals(expectedHighTemp, tommorowDomain.getHighTemp());
    }

    // Test case for testing the setLowTemp method
    public void testSetLowTemp() {
        int expectedLowTemp = 18;

        TommorowDomain tommorowDomain = new TommorowDomain("", "", "", 0, 0);

        tommorowDomain.setLowTemp(expectedLowTemp);

        assertEquals(expectedLowTemp, tommorowDomain.getLowTemp());
    }
}
