package com.example.weatherapp.Activities.SingleTon;

import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.res.Resources;

import junit.framework.TestCase;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the WeatherApiClient class.
 */
public class WeatherApiClientTest extends TestCase {

    @Mock
    Context mockContext;

    @Mock
    Resources mockResources;

    private WeatherApiClient weatherApiClient;

    /**
     * Set up method to initialize mocks and create an instance of WeatherApiClient.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        when(mockContext.getApplicationContext()).thenReturn(mockContext);
        when(mockContext.getResources()).thenReturn(mockResources);
        weatherApiClient = WeatherApiClient.getInstance(mockContext);
    }

    /**
     * Test case for getInstance method to ensure singleton behavior.
     */
    public void testGetInstance() {
        assertNotNull(weatherApiClient);
        WeatherApiClient secondInstance = WeatherApiClient.getInstance(mockContext);
        assertSame(weatherApiClient, secondInstance);
    }
}
