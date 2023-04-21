package com.example.tourlingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tourlingo.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements View.OnClickListener{
    Button btnLearn, btnProgress, btnSocial;
    FirebaseUser user;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialize();
    }

    private void initialize(){
        btnLearn = findViewById(R.id.btnLearn);
        btnLearn.setOnClickListener(this);
        btnProgress = findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(this);
        btnSocial = findViewById(R.id.btnSocial);
        btnSocial.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user==null) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();

        switch(vId){
            case R.id.btnLearn:
                goLearn();
                break;
            case R.id.btnProgress:
                goProgress();
                break;
            case R.id.btnSocial:
                goSocial();
                break;
        }
    }

    private void goLearn(){
        Intent i = new Intent(this, Learn.class);
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