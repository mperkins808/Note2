package com.example.note2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends AppCompatActivity {
    EditText newNoteEditText;
    String noteText;
    DatabaseHelper my_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        newNoteEditText = findViewById(R.id.newNoteEditText);
        my_db = new DatabaseHelper(this);

    }

    public void saveNoteCLick(View view) {
        noteText = newNoteEditText.getText().toString();
        if (my_db.insertData(noteText))
        {
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
        }

    }
}