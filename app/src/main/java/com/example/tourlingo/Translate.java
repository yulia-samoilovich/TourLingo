package com.example.tourlingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourlingo.model.Sentence;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Translate extends AppCompatActivity implements View.OnClickListener{
    TextView tvSentence, tvCorrect, tvIncorrect1, tvIncorrect2;
    TextView tvPoints;
    ImageView imRight, imWrong;
    DatabaseReference tourLingoDb;
    List<Sentence> questionList = new ArrayList<>();
    int count = 0;
    int points = 0;
    int minutes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        initialize();
        updateSentence();
    }

    private void initialize() {
        tvPoints = findViewById(R.id.tvPoints);
        tvSentence = findViewById(R.id.tvSentence);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvIncorrect1 = findViewById(R.id.tvIncorrect1);
        tvIncorrect2 = findViewById(R.id.tvIncorrect2);
        tvCorrect.setOnClickListener(this);
        tvIncorrect1.setOnClickListener(this);
        tvIncorrect2.setOnClickListener(this);
        imWrong = findViewById(R.id.imWrong);
        imRight = findViewById(R.id.imRight);

        tourLingoDb = FirebaseDatabase
                .getInstance()
                .getReference("sentences");
    }

    private void totalTime(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               String finalMinutes = String.valueOf(minutes);

               if(finalMinutes.length() == 1){
                   finalMinutes = "0"+finalMinutes;
               }

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.tvCorrect:
                imRight.setVisibility(View.VISIBLE);
                imRight.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imRight.setVisibility(View.INVISIBLE);
                        updateSentence();
                    }
                }, 1000);
                points = points + 10;
                updateScore();
                totalTime();
                break;

            case R.id.tvIncorrect1:
            case R.id.tvIncorrect2:
                imWrong.setVisibility(View.VISIBLE);
                imWrong.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imWrong.setVisibility(View.INVISIBLE);
                    }
                }, 1000);
                points = points - 5;
                updateScore();
                totalTime();
                break;
        }
    }

    private void updateScore() {
        tvPoints.setText("" + points);
    }

    private void updateSentence() {
        count++;
        if(count > 5){
            Intent i = new Intent(this, ProgressReview.class);
            i.putExtra("time", String.valueOf(minutes));
            i.putExtra("words", String.valueOf(count-1));
            i.putExtra("points", String.valueOf(points));
            startActivity(i);
        }
        else{
            tourLingoDb
                    .child(String.valueOf(count)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                String getQuestion = snapshot.child("sentence").getValue().toString();
                                String getAnswer = snapshot.child("answer").getValue().toString();
                                String getOption1 = snapshot.child("option1").getValue().toString();
                                String getOption2 = snapshot.child("option2").getValue().toString();

                                questionList.add(new Sentence(getQuestion, getAnswer, getOption1, getOption2));

                                for (Sentence oneSentence : questionList) {
                                    tvSentence.setText(oneSentence.getSentence());
                                    tvCorrect.setText(oneSentence.getAnswer());
                                    tvIncorrect1.setText(oneSentence.getOption1());
                                    tvIncorrect2.setText(oneSentence.getOption2());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }

}