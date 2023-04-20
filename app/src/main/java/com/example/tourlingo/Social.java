package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Social extends AppCompatActivity implements View.OnClickListener {
    Button btnLocation, btnBuddy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        initialize();
    }

    private void initialize() {
        btnLocation = findViewById(R.id.btnLocation);
        btnBuddy = findViewById(R.id.btnBuddy);
        btnLocation.setOnClickListener(this);
        btnBuddy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        
        switch(vId){
            case R.id.btnLocation:
                goLocation();
                break;
            case R.id.btnBuddy:
                goBuddy();
                break;
        }
    }

    private void goLocation() {
        //Intent i = new Intent(this, LocationActivity.class);
        //startActivity(i);
    }

    private void goBuddy() {
        //Intent i = new Intent(this, BuddyActivity.class);
        //startActivity(i);
    }
}