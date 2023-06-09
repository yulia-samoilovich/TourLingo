package com.example.tourlingo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tourlingo.model.Picture;
import com.example.tourlingo.model.Sentence;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MatchPicture extends AppCompatActivity implements View.OnClickListener {

    TextView tvAnswer, tvOption1, tvOption2;
    TextView tvPoints;
    ImageView imRight, imWrong, imLoad;
    DatabaseReference tourLingoDb;
    FirebaseStorage storage;
    StorageReference storageReference;
    ActivityResultLauncher actResLauncher;
    Uri filePath;
    int count = 0;
    int points = 0;
    int minutes = 0;
    List<Picture> pictureList = new ArrayList<>();
    List<Picture> matchSessions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_picture);
        initialize();
        updatePicture();
        startSession();
    }

    private void initialize() {
        tvAnswer = findViewById(R.id.tvAnswer);
        tvOption1 = findViewById(R.id.tvOption1);
        tvOption2 = findViewById(R.id.tvOption2);
        tvPoints = findViewById(R.id.tvPoints);
        tvAnswer.setOnClickListener(this);
        tvOption1.setOnClickListener(this);
        tvOption2.setOnClickListener(this);
        imLoad = findViewById(R.id.imLoad);
        imRight = findViewById(R.id.imRight);
        imWrong = findViewById(R.id.imWrong);

        tourLingoDb = FirebaseDatabase
                .getInstance()
                .getReference("pictures");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        registerActResLauncher();
    }

    private void registerActResLauncher() {
        actResLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        getPhoto(result);
                    }
                }
        );
    }

    private void getPhoto(ActivityResult result) {

        if (result.getResultCode()==RESULT_OK&&result.getData()!=null){

            filePath = result.getData().getData();
            try {
                Bitmap photo =
                        MediaStore
                                .Images
                                .Media
                                .getBitmap(getContentResolver(),filePath);
                imLoad.setImageBitmap(photo);
            }catch (Exception e){
                Log.d("Match picture",e.getMessage());
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.tvAnswer:
                imRight.setVisibility(View.VISIBLE);
                imRight.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imRight.setVisibility(View.INVISIBLE);
                        updatePicture();
                    }
                }, 1000);
                points = points + 10;
                updateScore();
                break;

            case R.id.tvOption1:
            case R.id.tvOption2:
                imWrong.setVisibility(View.VISIBLE);
                imWrong.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imWrong.setVisibility(View.INVISIBLE);
                    }
                }, 1000);
                points = points - 5;
                updateScore();
                break;
        }
    }

    private void startSession() {
        long startTime = System.currentTimeMillis();
        Picture session = new Picture(startTime);
        matchSessions.add(session);
    }

    private void finishSession() {
        long endTime = System.currentTimeMillis();
        int lastIndex = matchSessions.size() - 1;
        Picture lastSession = matchSessions.get(lastIndex);
        lastSession.setEndTime(endTime);
    }

    private int calculateTotalMinutes() {
        for (Picture session : matchSessions) {
            long sessionTimeMillis = session.getEndTime() - session.getStartTime();
            int sessionMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(sessionTimeMillis);
            minutes += sessionMinutes;
        }
        return minutes;
    }

    private void updateScore() {
        tvPoints.setText("" + points);
    }

    private void updatePicture() {
        count++;
        if(count > 5){
            finishSession();
            calculateTotalMinutes();
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
                            if (snapshot.exists()) {
                                String getAnswer = snapshot.child("answer").getValue().toString();
                                String getOption1 = snapshot.child("option1").getValue().toString();
                                String getOption2 = snapshot.child("option2").getValue().toString();

                                pictureList.add(new Picture(getAnswer, getOption1, getOption2));

                                for (Picture onePicture : pictureList) {
                                    tvAnswer.setText(onePicture.getAnswer());
                                    tvOption1.setText(onePicture.getOption1());
                                    tvOption2.setText(onePicture.getOption2());
                                }
                                String urlPhoto = snapshot
                                        .child("picture")
                                        .getValue()
                                        .toString();

                                Picasso
                                        .with(MatchPicture.this)
                                        .load(urlPhoto)
                                        .placeholder(R.drawable.loading)
                                        .into(imLoad);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }

}