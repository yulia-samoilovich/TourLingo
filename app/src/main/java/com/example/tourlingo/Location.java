package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Location extends AppCompatActivity implements View.OnClickListener {
    Button btnSave;
    EditText edCountry, edDate, edComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initialize();
    }

    private void initialize() {
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        edComment = findViewById(R.id.edCComment);
        edCountry = findViewById(R.id.edCountry);
        edDate = findViewById(R.id.edDate);
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();

        switch(vId){
            case R.id.btnSave:
                saveInfo();
                break;
        }
    }

    private void saveInfo() {
        Intent i = new Intent(this, SelectedLocation.class);
        startActivity(i);
    }

}