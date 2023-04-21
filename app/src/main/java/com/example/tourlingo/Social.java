package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Social extends AppCompatActivity implements View.OnClickListener {
    Button btnLocation;
    Button btnHome, btnProgress, btnSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        initialize();
    }

    private void initialize() {
        btnLocation = findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(this);

        btnHome = findViewById(R.id.btnHome);
        btnSocial = findViewById(R.id.btnSocial);
        btnProgress = findViewById(R.id.btnProgress);
        btnHome.setOnClickListener(this);
        btnSocial.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        
        switch(vId){
            case R.id.btnLocation:
                goLocation();
                break;
            case R.id.btnHome:
                goHome();
                break;
            case R.id.btnProgress:
                goProgress();
                break;
            case R.id.btnSocial:
                goSocial();
                break;
        }
    }

    private void goLocation() {
        Intent i = new Intent(this, Location.class);
        startActivity(i);
    }

    private void goHome() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }

    private void goProgress() {
        Intent i = new Intent(this, ProgressReview.class);
        startActivity(i);
    }

    private void goSocial() {
        Intent i = new Intent(this, Social.class);
        startActivity(i);
    }
}