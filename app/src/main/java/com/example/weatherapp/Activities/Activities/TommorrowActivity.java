package com.example.weatherapp.Activities.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Activities.Adapter.TommorowAdapter;
import com.example.weatherapp.Activities.Domains.TommorowDomain;
import com.example.weatherapp.Activities.Factory.DomainFactory;
import com.example.weatherapp.Activities.SingleTon.WeatherApiClient;
import com.example.weatherapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TommorrowActivity extends AppCompatActivity {
    private WeatherApiClient weatherApiClient;
    private RecyclerView.Adapter adapterTommorow;
    private RecyclerView recyclerView;

    private TextView windTxt, humidityTxt, tempTodayText, rainTxt, statusTxt;

    /**
     * Initializes the activity layout, views, and data.
     * Fetches weather data for tomorrow and updates the UI accordingly.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state, or null if there was none.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tommorrow);

        weatherApiClient = WeatherApiClient.getInstance(this); // Initialize the singleton

        // Apply edge-to-edge for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        windTxt = findViewById(R.id.tomorrowWindSpeed);
        humidityTxt = findViewById(R.id.tomorrowHumidity);
        tempTodayText = findViewById(R.id.tomorrowTempText);
        rainTxt = findViewById(R.id.tomorrowRainProb);
        statusTxt = findViewById(R.id.tomorrowTempStatus);

        initRecyclerView();
        setVariable();

        fetchWeatherData("tomorrow");
    }

    /**
     * Sets click listener for back button to navigate back to MainActivity.
     */
    private void setVariable() {
        ConstraintLayout backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TommorrowActivity.this, MainActivity.class));
            }
        });
    }

    /**
     * Initializes the RecyclerView for displaying tomorrow's weather forecast.
     */
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Initialize the adapter and set it to the RecyclerView
        adapterTommorow = new TommorowAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapterTommorow);
    }

    /**
     * Fetches weather data for tomorrow from the API asynchronously.
     *
     * @param c The query parameter to specify tomorrow's weather data.
     */
    private void fetchWeatherData(String c) {
        new FetchWeatherTask().execute(c);
    }

    /**
     * AsyncTask to fetch weather data asynchronously.
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
                handleWeatherData(result);
            }
        }
    }

    /**
     * Parses the JSON weather data received from the API response and updates the UI.
     *
     * @param weatherData The JSON string containing the weather data.
     */
    private void handleWeatherData(String weatherData) {
        try {
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(weatherData);

            // Extract values from the JSON object
            String str = jsonObject.getString("status");
            int temperature = jsonObject.getInt("temperature");
            double rainProbability = jsonObject.getDouble("probabilityOfRain");
            double windSpeed = jsonObject.getDouble("windSpeed");
            double humidity = jsonObject.getDouble("humidity");

            // Set values to TextViews
            statusTxt.setText(str);
            tempTodayText.setText(String.valueOf(temperature));
            rainTxt.setText(String.valueOf(rainProbability * 100));
            windTxt.setText(String.valueOf(windSpeed));
            humidityTxt.setText(String.valueOf(humidity * 100));

            // Parse the forecast array for tomorrow
            JSONArray forecastArray = jsonObject.getJSONArray("forecast");
            ArrayList<TommorowDomain> items = new ArrayList<>();

            for (int i = 0; i < forecastArray.length(); i++) {
                JSONObject forecastObject = forecastArray.getJSONObject(i);
                String day = forecastObject.getString("day");
                String status = forecastObject.getString("status");
                int highTemperature = forecastObject.getInt("highestTemperature");
                int lowTemperature = forecastObject.getInt("lowestTemperature");
                // Create a TomorrowDomain object and add it to the list
                items.add((TommorowDomain) DomainFactory.createDomain("Tommorow", day, status, status, highTemperature, lowTemperature));
            }

            // Update the RecyclerView with the new data
            adapterTommorow = new TommorowAdapter(TommorrowActivity.this, items);
            recyclerView.setAdapter(adapterTommorow);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
