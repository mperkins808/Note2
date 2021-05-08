package com.example.note2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteEditActivity extends AppCompatActivity {
    EditText editNoteView;
    Button updateButton;
    Button deleteButton;
    SharedPreferences sp;
    DatabaseHelper db;
    String noteToDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        editNoteView = findViewById(R.id.editNoteTextView);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        db = new DatabaseHelper(this);
        SharedPreferences sp = getSharedPreferences("com.example.note2", MODE_PRIVATE);
        int listPosition = sp.getInt("listPos", 0);
        List<String> noteList = db.fetchAllNotes();
        sp.edit().putString("noteToDelete", noteList.get(listPosition)).apply();
        noteToDelete = noteList.get(listPosition);
        Log.e("L", noteList.get(listPosition));
        editNoteView.setText(noteList.get(listPosition));
    }

    public void updateClick(View view) {
        db.deleteEntry(noteToDelete);
        db.insertData(editNoteView.getText().toString());
        Intent intent = new Intent(this, CurrentNotesActivity.class);
        startActivity(intent);
    }

    public void deleteClick(View view){
        editNoteView.setText("");
        db.deleteEntry(noteToDelete);
        Intent intent = new Intent(this, CurrentNotesActivity.class);
        startActivity(intent);
    }
}