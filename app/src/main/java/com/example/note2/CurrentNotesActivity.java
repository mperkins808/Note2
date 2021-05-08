
package com.example.note2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CurrentNotesActivity extends AppCompatActivity {
    ListView noteListView;
    ArrayList<String> noteArray;
    ArrayAdapter<String> adapter;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_notes);
        noteListView = findViewById(R.id.listView);
        noteArray = new ArrayList<String>();
        DatabaseHelper db = new DatabaseHelper(this);
        sp = getSharedPreferences("com.example.note2", MODE_PRIVATE);
        List<String> noteList = db.fetchAllNotes();
        for (String note: noteList){
            noteArray.add(note);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noteArray);
        noteListView.setAdapter(adapter);

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoteEditActivity.class);
                Log.e("L", "list position:" + String.valueOf(position));
                intent.putExtra("notesEdit", noteArray.get(position));
                startActivityForResult(intent, 1);
                sp.edit().putInt("listPos", position).apply();

            }
        });


    }
}