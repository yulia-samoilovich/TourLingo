package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.tourlingo.model.Country;
import com.example.tourlingo.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class Location extends AppCompatActivity implements View.OnClickListener {
    Button btnSave, btnReturn;
    EditText edCountry, edDate, edComment;
    List<Country> countryList = new ArrayList<>();
    ArrayAdapter<Country> countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initialize();
    }

    private void initialize() {
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnReturn = findViewById(R.id.btnReturn);
        edComment = findViewById(R.id.edComment);
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
            case R.id.btnReturn:
                finish();
                break;
        }
    }

    private void saveInfo() {
        String country = edCountry.getText().toString();
        String comment = edComment.getText().toString();
        String date = edDate.getText().toString();

        countryList.add(new Country(country, comment, date));

        Intent i = new Intent(this, SelectedLocation.class);
        i.putExtra("country", country);
        i.putExtra("comment", comment);
        i.putExtra("date", date);
        startActivity(i);
    }

}