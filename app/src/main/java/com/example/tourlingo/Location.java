package com.example.tourlingo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    public void openLocationActivity(View view) {
        // 각 버튼 클릭 시 호출되는 메소드입니다.
        // 클릭된 버튼의 id를 이용하여 다음 화면으로 넘어갑니다.
        switch (view.getId()) {
            case R.id.moscow_button:
                startActivity(new Intent(this, Moscow.class));
                break;
            case R.id.seoul_button:
                startActivity(new Intent(this, Seoul.class));
                break;
            case R.id.vancouver_button:
                startActivity(new Intent(this, Vancouver.class));
                break;
            default:
                break;
        }
    }

    public void openSocialMain(View view) {
        // 뒤로가기 버튼 클릭 시 호출되는 메소드입니다.
        finish();
    }
}

