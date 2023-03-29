package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    public void onClick(View view) {
        int vId = view.getId();

        switch(vId){
            case R.id.btnRegister:
                Intent i = new Intent(this, Register.class);
                startActivity(i);
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
        }
    }
}