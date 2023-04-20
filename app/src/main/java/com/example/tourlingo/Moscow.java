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

public class Moscow extends AppCompatActivity {

    EditText edMoscowComment, edMoscowComment1;
    Button moscowsave_button, moscowmodify_button, moscowdelete_button;
    ListView moscowview;
    ArrayList<String> commentList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moscow);

        edMoscowComment = findViewById(R.id.edMoscowComment);
        edMoscowComment1 = findViewById(R.id.edMoscowComment1);
        moscowsave_button = findViewById(R.id.moscowsave_button);
        moscowmodify_button = findViewById(R.id.moscowmodify_button);
        moscowdelete_button = findViewById(R.id.moscowdelete_button);
        moscowview = findViewById(R.id.moscowview);

        commentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentList);
        moscowview.setAdapter(adapter);

        moscowsave_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = edMoscowComment.getText().toString();
                String comment1 = edMoscowComment1.getText().toString();
                String data = comment + "\n" + comment1;
                commentList.add(data);
                adapter.notifyDataSetChanged();
                edMoscowComment.setText("");
                edMoscowComment1.setText("");
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
            }
        });

        moscowmodify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });

        moscowview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = commentList.get(position);
                String[] comments = data.split("\n");
                edMoscowComment.setText(comments[0]);
                edMoscowComment1.setText(comments[1]);
            }
        });

        moscowdelete_button.setOnClickListener(new View.OnClickListener() {
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
