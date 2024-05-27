package com.example.weatherapp.Activities.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; // Define TAG constant
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;
    private WeatherApiClient weatherApiClient;
    private TextView windTxt, humidityTxt, tempTodayText, rainTxt, statusTxt, highLowTxt;

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

        // Fetch weather data
        fetchWeatherData("today");
    }

    private void setVariable() {
        TextView next7DayBtn = findViewById(R.id.nextBtn);
        next7DayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TommorrowActivity.class));
            }
        });
    }

    private void initRecyclerview() {
        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Initialize with empty data until fetched
        adapterHourly = new HourlyAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapterHourly);
    }

    private void fetchWeatherData(String city) {
        new FetchWeatherTask().execute(city);
    }

    private class FetchWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String city = params[0];
            return weatherApiClient.fetchWeatherData(city);
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
                    rainTxt.setText(String.valueOf(rainProbability*100));
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
}
