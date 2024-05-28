package com.example.weatherapp.Activities.SingleTon;

import android.content.Context;
import android.util.Log;

import com.example.weatherapp.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherApiClient {
    private static final String TAG = "WeatherApiClient"; // Define TAG constant
    private static WeatherApiClient instance;
    private final OkHttpClient client;
    private final Context context;

    WeatherApiClient(Context context) {
        this.context = context.getApplicationContext(); // Prevent memory leaks
        client = new OkHttpClient.Builder().build();
    }

    public static synchronized WeatherApiClient getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherApiClient(context);
        }
        return instance;
    }

    public String fetchWeatherData(String c) {
        String apiKey = context.getString(R.string.weather_api_key);
        String lat = context.getString(R.string.lat);
        String lon = context.getString(R.string.lon);

//        String url = "https://api.openweathermap.org/data/3.0/onecall?lat="+ lat +"&lon=" + lon +"&units=metric&lang=en&appid="+apiKey;
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
