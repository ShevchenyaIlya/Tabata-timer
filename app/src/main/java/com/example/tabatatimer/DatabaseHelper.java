package com.example.tabatatimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WorkoutDatabase.db";
    private static final int version = 1;
    private static final String TABLE_NAME = "trainings";
    private static final String[] Columns = new String[] {"id", "workout_name", "preparation_time", "stretch_time",
            "work_count", "work_time", "relax_count", "relax_time", "cycles_number", "sets_number", "relax_after_set_time"};


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (String value: Columns) {
            createTable += value + " INTEGER, ";
        }
        createTable = createTable.substring(0, createTable.length() - 2);
        createTable = createTable + ")";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns[1], item);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }
}
