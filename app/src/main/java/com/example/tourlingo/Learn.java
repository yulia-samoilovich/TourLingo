package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Learn extends AppCompatActivity implements View.OnClickListener{
    Button btnTranslate, btnMatchP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        initialize();
    }

    private void initialize() {
        //btnArrange = findViewById(R.id.btnArrange);
        btnTranslate = findViewById(R.id.btnTranslate);
        //btnInsert = findViewById(R.id.btnInsert);
        //btnMatchT = findViewById(R.id.btnMatchT);
        btnMatchP = findViewById(R.id.btnMatchP);
        //btnArrange.setOnClickListener(this);
        btnTranslate.setOnClickListener(this);
        //btnInsert.setOnClickListener(this);
        //btnMatchT.setOnClickListener(this);
        btnMatchP.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int btnId = view.getId();

        switch(btnId){
            //case R.id.btnArrange:
            //Intent i = new Intent(this, Arrange.class);
            //startActivity(i);
            //break;
            case R.id.btnTranslate:
                Intent i2 = new Intent(this, Translate.class);
                startActivity(i2);
                break;
            case R.id.btnMatchP:
                Intent i3 = new Intent(this, MatchPicture.class);
                startActivity(i3);
        }
    }
}