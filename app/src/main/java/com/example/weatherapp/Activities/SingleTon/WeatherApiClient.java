package com.example.weatherapp.Activities.SingleTon;

import android.content.Context;
import android.util.Log;

import com.example.weatherapp.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A singleton class for making API requests to fetch weather data.
 */
public class WeatherApiClient {
    private static final String TAG = "WeatherApiClient"; // Define TAG constant
    private static WeatherApiClient instance;
    private final OkHttpClient client;
    private final Context context;

    /**
     * Private constructor to prevent direct instantiation. Use getInstance() method instead.
     *
     * @param context the application context
     */
    private WeatherApiClient(Context context) {
        this.context = context.getApplicationContext(); // Prevent memory leaks
        client = new OkHttpClient.Builder().build();
    }

    /**
     * Returns the singleton instance of WeatherApiClient.
     *
     * @param context the application context
     * @return the singleton instance of WeatherApiClient
     */
    public static synchronized WeatherApiClient getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherApiClient(context);
        }
        return instance;
    }

    /**
     * Fetches weather data from the API.
     *
     * @param c the city for which weather data is requested
     * @return a JSON string containing weather data, or null if an error occurs
     */
    public String fetchWeatherData(String c) {
        String apiKey = context.getString(R.string.weather_api_key);
        String url = "https://weatherserviceapi.onrender.com/weather/" + c;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                Log.e(TAG, "Failed to fetch weather data. Response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, "Exception occurred while fetching weather data: " + e.getMessage());
            return null;
        }
    }
}
