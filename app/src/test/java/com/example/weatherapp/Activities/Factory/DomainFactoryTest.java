package com.example.weatherapp.Activities.Factory;

import static org.junit.Assert.assertTrue;

import com.example.weatherapp.Activities.Domains.Hourly;

import org.junit.Test;

/**
 * Unit tests for the DomainFactory class.
 */
public class DomainFactoryTest {

    /**
     * Test case for creating an Hourly domain.
     */
    @Test
    public void testCreateHourlyDomain() {
        Object domain = DomainFactory.createDomain("Hourly", "12:00", 25.5, "path/to/pic.jpg");
        assertTrue(domain instanceof Hourly);
    }

    /**
     * Test case for creating a domain with an invalid type.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDomainType() {
        DomainFactory.createDomain("InvalidType", "args");
    }

    /**
     * Test case for creating an Hourly domain with invalid arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHourlyArguments() {
        DomainFactory.createDomain("Hourly", "12:00", "invalidTemp", "path/to/pic.jpg");
    }

    /**
     * Test case for creating a Tomorrow domain with invalid arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTomorrowArguments() {
        DomainFactory.createDomain("Tomorrow", "Monday", "path/to/pic.jpg", "Sunny", "invalidHighTemp", "invalidLowTemp");
    }
}
