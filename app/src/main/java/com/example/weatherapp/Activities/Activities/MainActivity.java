package com.example.weatherapp.Activities.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Activities.Adapter.HourlyAdapter;
import com.example.weatherapp.Activities.Domains.Hourly;
import com.example.weatherapp.Activities.Factory.DomainFactory;
import com.example.weatherapp.Activities.SingleTon.WeatherApiClient; // Import the singleton
import com.example.weatherapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The main activity displaying weather information and hourly forecast.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; // Define TAG constant
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;
    private WeatherApiClient weatherApiClient;
    private TextView windTxt, humidityTxt, tempTodayText, rainTxt, statusTxt, highLowTxt;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherApiClient = WeatherApiClient.getInstance(this); // Initialize the singleton

        // Applying edge-to-edge for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        windTxt = findViewById(R.id.windSpeedText);
        humidityTxt = findViewById(R.id.humidityText);
        tempTodayText = findViewById(R.id.tempTodayText);
        rainTxt = findViewById(R.id.rainProbabilityText);
        statusTxt = findViewById(R.id.statusTodayText);
        highLowTxt = findViewById(R.id.highLowText);

        initRecyclerview();
        setVariable();

        checkLocationPermission();

        // Fetch weather data
        fetchWeatherData("today");
    }

    /**
     * Initializes the RecyclerView for displaying hourly forecast.
     */
    private void initRecyclerview() {
        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Initialize with empty data until fetched
        adapterHourly = new HourlyAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapterHourly);
    }

    /**
     * Fetches weather data from the API.
     *
     * @param c the city for which weather data is requested
     */
    private void fetchWeatherData(String c) {
        new FetchWeatherTask().execute(c);
    }

    /**
     * Sets click listener for the "Next 7 Days" button.
     */
    private void setVariable() {
        TextView next7DayBtn = findViewById(R.id.nextBtn);
        next7DayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TommorrowActivity.class));
            }
        });
    }

    /**
     * AsyncTask to fetch weather data from the API in the background.
     */
    private class FetchWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String c = params[0];
            return weatherApiClient.fetchWeatherData(c);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d(TAG, "API response received: " + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);

                    // Parse the JSON response and update the UI accordingly
                    double temperature = jsonObject.getDouble("temperature");
                    double humidity = jsonObject.getDouble("humidity");
                    double windSpeed = jsonObject.getDouble("windSpeed");
                    String str = jsonObject.getString("status");
                    double rainProbability = jsonObject.getDouble("probabilityOfRain");
                    double highTemperature = jsonObject.getDouble("highestTemperature");
                    double lowTemperature = jsonObject.getDouble("lowestTemperature");

                    String highLowText = "H: " + highTemperature + "\u00B0C" + " " + " L: " + lowTemperature + "\u00B0C";

                    // Update the UI with the parsed data
                    tempTodayText.setText(String.valueOf(temperature));
                    humidityTxt.setText(String.valueOf(humidity * 100));
                    windTxt.setText(String.valueOf(windSpeed));
                    rainTxt.setText(String.valueOf(rainProbability * 100));
                    statusTxt.setText(str);
                    highLowTxt.setText(highLowText);

                    // Parse the hourly forecast array
                    JSONArray hourlyForecastArray = jsonObject.getJSONArray("hourlyForecast");
                    ArrayList<Hourly> items = new ArrayList<>();

                    for (int i = 0; i < hourlyForecastArray.length(); i++) {
                        JSONObject hourlyObject = hourlyForecastArray.getJSONObject(i);

                        String hour = hourlyObject.getString("hour");
                        double hourlyTemperature = hourlyObject.getDouble("temperature");
                        String status = hourlyObject.getString("status");

                        // Add the Hourly object to the items list
                        items.add((Hourly) DomainFactory.createDomain("Hourly", hour, hourlyTemperature, status));
                    }

                    // Update the RecyclerView with the new data
                    adapterHourly = new HourlyAdapter(MainActivity.this, items);
                    recyclerView.setAdapter(adapterHourly);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

/**
 * Checks if the app has location permission.
*/
 private void checkLocationPermission() {
 if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
 ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
 } else {
 displayLastKnownLocation();
 }
 }

 /**
 * Displays the last known location of the device.
 */
private void displayLastKnownLocation() {
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    if (locationManager != null) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                double latitude = lastKnownLocation.getLatitude();
                double longitude = lastKnownLocation.getLongitude();
                String latLongMsg = "Latitude: " + latitude + ", Longitude: " + longitude;
                Toast.makeText(this, latLongMsg, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
}
