package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProgressReview extends AppCompatActivity {
    TextView tvTime, tvWords, tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }
}