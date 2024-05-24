package com.example.weatherapp.Activities.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Activities.Adapter.HourlyAdapter;
import com.example.weatherapp.Activities.Domains.Hourly;
import com.example.weatherapp.Activities.Factory.DomainFactory;
import com.example.weatherapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initRecyclerview();

        setVariable();

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
        ArrayList<Hourly> items = new ArrayList<>();
        items.add((Hourly) DomainFactory.createDomain("Hourly", "10 PM", 28, "cloudy"));
        items.add((Hourly) DomainFactory.createDomain("Hourly", "11 PM", 29, "sunny"));
        items.add((Hourly) DomainFactory.createDomain("Hourly", "12 PM", 30, "wind"));
        items.add((Hourly) DomainFactory.createDomain("Hourly", "01 AM", 29, "rainy"));
        items.add((Hourly) DomainFactory.createDomain("Hourly", "02 AM", 27, "storm"));

        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterHourly = new HourlyAdapter(this, items);
        recyclerView.setAdapter(adapterHourly);
    }
}