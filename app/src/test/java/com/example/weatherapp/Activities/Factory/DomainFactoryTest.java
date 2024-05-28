package com.example.weatherapp.Activities.Factory;

import static org.junit.Assert.assertTrue;

import com.example.weatherapp.Activities.Domains.Hourly;

import org.junit.Test;

public class DomainFactoryTest {

    @Test
    public void testCreateHourlyDomain() {
        Object domain = DomainFactory.createDomain("Hourly", "12:00", 25.5, "path/to/pic.jpg");
        assertTrue(domain instanceof Hourly);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDomainType() {
        DomainFactory.createDomain("InvalidType", "args");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHourlyArguments() {
        DomainFactory.createDomain("Hourly", "12:00", "invalidTemp", "path/to/pic.jpg");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTomorrowArguments() {
        DomainFactory.createDomain("Tomorrow", "Monday", "path/to/pic.jpg", "Sunny", "invalidHighTemp", "invalidLowTemp");
    }
}
