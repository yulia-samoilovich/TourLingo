package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tourlingo.model.Country;

import java.util.List;

public class SelectedLocation extends AppCompatActivity implements View.OnClickListener {
    Button btnModify, btnDelete, btnReturn;
    TextView tvCountry, tvComment, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_location);
        initialize();
    }

    private void initialize() {
        btnModify = findViewById(R.id.btnModify);
        btnDelete = findViewById(R.id.btnDelete);
        tvComment = findViewById(R.id.tvComment);
        tvCountry = findViewById(R.id.tvCountry);
        tvDate = findViewById(R.id.tvDate);
        btnModify.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);

        getSocialInfo();
    }

    private void getSocialInfo() {
        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        String comment = intent.getStringExtra("comment");
        String date = intent.getStringExtra("date");

        tvCountry.setText(country);
        tvComment.setText(comment);
        tvDate.setText(date);
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();

        switch(vId){
            case R.id.btnModify:
                modifyInfo();
                break;
            case R.id.btnDelete:
                deleteInfo();
                break;
            case R.id.btnReturn:
                Intent i = new Intent(this, Social.class);
                startActivity(i);
                break;
        }
    }

    private void deleteInfo() {
        tvCountry.setText(null);
        tvComment.setText(null);
        tvDate.setText(null);
    }

    private void modifyInfo() {
        finish();
    }
}