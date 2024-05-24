package com.example.weatherapp.Activities.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
import com.example.weatherapp.R;

import java.util.ArrayList;

public class TommorrowActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterTommorow;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tommorrow);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initRecyclerView();
        setVariable();
    }

    private void setVariable() {
        ConstraintLayout backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TommorrowActivity.this,MainActivity.class));
            }
        });
    }

    private void initRecyclerView() {
        ArrayList<TommorowDomain> items = new ArrayList<>();
        items.add((TommorowDomain) DomainFactory.createDomain("Tommorow", "Sat", "storm", "Storm", 25, 10));
        items.add((TommorowDomain) DomainFactory.createDomain("Tommorow", "Sun", "cloudy", "Rainy_Sunny", 25, 10));
        items.add((TommorowDomain) DomainFactory.createDomain("Tommorow", "Mon", "cloudy_3", "Cloudy", 25, 10));
        items.add((TommorowDomain) DomainFactory.createDomain("Tommorow", "Tue", "cloudy_sunny", "Cloudy_Sunny", 25, 10));
        items.add((TommorowDomain) DomainFactory.createDomain("Tommorow", "Wed", "sun", "Sunny", 25, 10));
        items.add((TommorowDomain) DomainFactory.createDomain("Tommorow", "Thu", "rainy", "Rainy", 25, 10));

        recyclerView = findViewById(R.id.view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Initialize the adapter and set it to the RecyclerView
        adapterTommorow = new TommorowAdapter(this, items);
        recyclerView.setAdapter(adapterTommorow);
    }
}