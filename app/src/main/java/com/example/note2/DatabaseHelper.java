package com.example.note2;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "notes_database";
    public static final String TABLE_NAME = "note_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "note_content";

    public static final String ID = "ID";
    public static final String NOTE_CONTENT = "name";
    public static final String NOTE_TO_DELETE = "delete_node";

    SharedPreferences sp;
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CITY = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY , " + NOTE_CONTENT + " TEXT)";
        db.execSQL(CREATE_TABLE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String notes_content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE_CONTENT, notes_content);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result > 0){
            return true;
        }
        else return false;
    }

    public List<String> fetchAllNotes(){
        List<String> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()){
            do {
                String note = cursor.getString(1);
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        db.close();
        return noteList;
    }
    public void clearDatabase(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    public void deleteEntry(String entry){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e("db", "Deleting entry:" + entry);
        String command = "delete from "+TABLE_NAME+" where name='"+entry+"'";
        db.execSQL(command);
        db.close();
    }

}
