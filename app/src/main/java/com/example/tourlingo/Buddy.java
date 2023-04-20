package com.example.tourlingo;

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

public class Buddy extends AppCompatActivity {
    // UI components
    EditText edCWhere, edCWhen, edCComment;
    Button buddySaveButton, buddyModifyButton;
    ListView findBuddyView;

    // ArrayList to store buddy information
    ArrayList<String> buddyList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findbuddy);

        // Initialize UI components
        edCWhere = findViewById(R.id.edCWhere);
        edCWhen = findViewById(R.id.edCWhen);
        edCComment = findViewById(R.id.edCComment);
        buddySaveButton = findViewById(R.id.buddysave_button);
        buddyModifyButton = findViewById(R.id.buddymodify_button);
        findBuddyView = findViewById(R.id.findbuddyview);

        // Initialize buddy list and adapter
        buddyList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buddyList);
        findBuddyView.setAdapter(adapter);

        // Set click listener for save button
        buddySaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get information from EditTexts
                String where = edCWhere.getText().toString();
                String when = edCWhen.getText().toString();
                String comment = edCComment.getText().toString();

                // Validate input
                if (where.isEmpty() || when.isEmpty()) {
                    Toast.makeText(Buddy.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Add information to buddy list and update adapter
                    String buddyInfo = where + " - " + when + " - " + comment;
                    buddyList.add(buddyInfo);
                    adapter.notifyDataSetChanged();

                    // Clear EditTexts
                    edCWhere.setText("");
                    edCWhen.setText("");
                    edCComment.setText("");

                    Toast.makeText(Buddy.this, "Buddy information saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for list items
        findBuddyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get selected buddy information
                String buddyInfo = buddyList.get(position);

                // Set information to EditTexts
                String[] infoArray = buddyInfo.split(" - ");
                edCWhere.setText(infoArray[0]);
                edCWhen.setText(infoArray[1]);
                edCComment.setText(infoArray[2]);

                // Remove selected item from list and update adapter
                buddyList.remove(position);
                adapter.notifyDataSetChanged();

                Toast.makeText(Buddy.this, "Buddy information modified", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
