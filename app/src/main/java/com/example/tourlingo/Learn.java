package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Learn extends AppCompatActivity implements View.OnClickListener{
    Button btnTranslate, btnMatchP;
    Button btnHome, btnProgress, btnSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        initialize();
    }

    private void initialize() {
        btnTranslate = findViewById(R.id.btnTranslate);
        btnMatchP = findViewById(R.id.btnMatchP);
        btnTranslate.setOnClickListener(this);
        btnMatchP.setOnClickListener(this);

        btnHome = findViewById(R.id.btnHome);
        btnSocial = findViewById(R.id.btnSocial);
        btnProgress = findViewById(R.id.btnProgress);
        btnHome.setOnClickListener(this);
        btnSocial.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int btnId = view.getId();

        switch(btnId){
            case R.id.btnTranslate:
                Intent i2 = new Intent(this, Translate.class);
                startActivity(i2);
                break;
            case R.id.btnMatchP:
                Intent i3 = new Intent(this, MatchPicture.class);
                startActivity(i3);
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