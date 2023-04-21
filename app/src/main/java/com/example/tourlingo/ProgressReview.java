package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProgressReview extends AppCompatActivity implements View.OnClickListener {
    TextView tvTime, tvWords, tvPoints;
    Button btnHome, btnProgress, btnSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_review);
        initialize();
    }

    private void initialize() {
        tvTime = findViewById(R.id.tvTime);
        tvWords = findViewById(R.id.tvWords);
        tvPoints = findViewById(R.id.tvPoints);

        Intent i = getIntent();

        String time = i.getStringExtra("time");
        String words = i.getStringExtra("words");
        String points = i.getStringExtra("points");

        tvTime.setText(time);
        tvWords.setText(words);
        tvPoints.setText(points);

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