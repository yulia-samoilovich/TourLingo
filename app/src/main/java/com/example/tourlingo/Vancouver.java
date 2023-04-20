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

public class Vancouver extends AppCompatActivity {

    EditText edVancouverComment, edVancouverComment1;
    Button vancouversave_button, vancouvermodify_button, vancouverdelete_button;
    ListView vancouverview;
    ArrayList<String> commentList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vancouver);

        edVancouverComment = findViewById(R.id.edVancouverComment);
        edVancouverComment1 = findViewById(R.id.edVancouverComment1);
        vancouversave_button = findViewById(R.id.vancouversave_button);
        vancouvermodify_button = findViewById(R.id.vancouvermodify_button);
        vancouverdelete_button = findViewById(R.id.vancouverdelete_button);
        vancouverview = findViewById(R.id.vancouverview);

        commentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentList);
        vancouverview.setAdapter(adapter);

        vancouversave_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = edVancouverComment.getText().toString();
                String comment1 = edVancouverComment1.getText().toString();
                String data = comment + "\n" + comment1;
                commentList.add(data);
                adapter.notifyDataSetChanged();
                edVancouverComment.setText("");
                edVancouverComment1.setText("");
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
            }
        });

        vancouvermodify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });

        vancouverview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = commentList.get(position);
                String[] comments = data.split("\n");
                edVancouverComment.setText(comments[0]);
                edVancouverComment1.setText(comments[1]);
            }
        });

        vancouverdelete_button.setOnClickListener(new View.OnClickListener() {
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
