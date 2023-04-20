package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Location extends AppCompatActivity implements View.OnClickListener {
    Button btnMoscow, btnSeoul, btnVancouver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initialize();
    }

    private void initialize() {
        btnMoscow = findViewById(R.id.btnMoscow);
        btnMoscow.setOnClickListener(this);
        btnSeoul = findViewById(R.id.btnSeoul);
        btnSeoul.setOnClickListener(this);
        btnVancouver = findViewById(R.id.btnVancouver);
        btnVancouver.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();

        switch(vId){
            case R.id.btnMoscow:
                goMoscow();
                break;
            case R.id.btnSeoul:
                goSeoul();
                break;
            case R.id.btnVancouver:
                goVancouver();
                break;
        }
    }

    private void goVancouver() {
        Intent i = new Intent(this, Vancouver.class);
        startActivity(i);
    }

    private void goSeoul() {
        Intent i = new Intent(this, Seoul.class);
        startActivity(i);
    }

    private void goMoscow() {
        Intent i = new Intent(this, Moscow.class);
        startActivity(i);
    }
}