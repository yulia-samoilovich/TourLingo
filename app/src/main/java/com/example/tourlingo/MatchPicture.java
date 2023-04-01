package com.example.tourlingo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.List;

public class MatchPicture extends AppCompatActivity implements View.OnClickListener {

    TextView tvAnswer, tvOption1, tvOption2;
    ImageView imRight, imWrong, imLoad;
    DatabaseReference tourLingoDb;
    FirebaseStorage storage;
    StorageReference storageReference,sRef;
    ActivityResultLauncher actResLauncher;
    Uri filePath;
    ProgressDialog prDialog;
    int count = 0;
    int points = 0;
    List<Picture> pictureList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_picture);
        initialize();
    }

    private void initialize() {
        tvAnswer = findViewById(R.id.tvAnswer);
        tvOption1 = findViewById(R.id.tvOption1);
        tvOption2 = findViewById(R.id.tvOption2);
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
                        actResLauncher = registerForActivityResult(
                                new ActivityResultContracts.StartActivityForResult(),
                                new ActivityResultCallback<ActivityResult>() {
                                    @Override
                                    public void onActivityResult(ActivityResult result) {
                                        getPhoto(result);
                                    }
                                }
                        );
                        updatePicture();
                    }
                }, 1000);
                points++;
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
                break;
        }
    }

    private void updatePicture() {
        count++;
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
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}