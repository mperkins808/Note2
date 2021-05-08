package com.example.note2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper my_db;
    Button mybutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_db = new DatabaseHelper(this);
        mybutton = findViewById(R.id.newNoteButton);
        //my_db.clearDatabase();
    }

    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.newNoteButton:
                Intent intent = new Intent(this, NewNoteActivity.class);
                startActivity(intent);
                break;

            case R.id.showNotesButton:
                 Intent showIntent= new Intent(this, CurrentNotesActivity.class);
                startActivity(showIntent);
                break;

            default:
                throw new IndexOutOfBoundsException();

        }

    }

}