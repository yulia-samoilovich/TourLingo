package com.example.tourlingo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Seoul extends AppCompatActivity {

    EditText edSeoulComment, edSeoulComment1;
    Button seoulsave_button, seoulmodify_button, seouldelete_button;
    ListView seoulview;
    ArrayList<String> commentList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul);

        edSeoulComment = findViewById(R.id.edSeoulComment);
        edSeoulComment1 = findViewById(R.id.edSeoulComment1);
        seoulsave_button = findViewById(R.id.seoulsave_button);
        seoulmodify_button = findViewById(R.id.seoulmodify_button);
        seouldelete_button = findViewById(R.id.seouldelete_button);
        seoulview = findViewById(R.id.seoulview);

        commentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentList);
        seoulview.setAdapter(adapter);

        seoulsave_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = edSeoulComment.getText().toString();
                String comment1 = edSeoulComment1.getText().toString();
                String data = comment + "\n" + comment1;
                commentList.add(data);
                adapter.notifyDataSetChanged();
                edSeoulComment.setText("");
                edSeoulComment1.setText("");
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
            }
        });

        seoulmodify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });

        seoulview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = commentList.get(position);
                String[] comments = data.split("\n");
                edSeoulComment.setText(comments[0]);
                edSeoulComment1.setText(comments[1]);
            }
        });

        seouldelete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentList.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "All Data deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openLocationActivity(View view) {
        Intent intent = new Intent(this, Location.class);
        startActivity(intent);
    }
}
