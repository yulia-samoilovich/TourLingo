package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Social extends AppCompatActivity implements View.OnClickListener {
    Button btnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        initialize();
    }

    private void initialize() {
        btnLocation = findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        
        switch(vId){
            case R.id.btnLocation:
                goLocation();
                break;
        }
    }

    private void goLocation() {
        Intent i = new Intent(this, Location.class);
        startActivity(i);
    }
}