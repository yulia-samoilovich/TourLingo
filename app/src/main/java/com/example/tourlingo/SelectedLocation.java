package com.example.tourlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectedLocation extends AppCompatActivity implements View.OnClickListener {
    Button btnModify, btnDelete;
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
        }
    }

    private void deleteInfo() {
    }

    private void modifyInfo() {
    }
}