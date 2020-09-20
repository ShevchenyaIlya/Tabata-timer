package com.example.tabatatimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WorkoutDatabase2.db";
    private static final int version = 1;
    private static final String TABLE_NAME = "training";
    private static final String[] Columns = new String[] {"id", "workout_name", "preparation_time", "stretch_time",
            "work_count", "work_time", "relax_count", "relax_time", "cycles_number", "sets_number", "relax_after_set_time"};


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, workout_name TEXT, ";
        for (String value: Columns) {
            if (!value.equals("id") && !value.equals("workout_name")) {
                String insertColumn = value + " INTEGER, ";
                createTable = createTable + insertColumn;
            }
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

    protected ContentValues createRow(String workout_name, int preparation_time, int stretch_time, int work_count, int work_time,
                                      int relax_count, int relax_time, int cycles_number, int sets_number, int relax_after_set_time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns[1], workout_name);
        contentValues.put(Columns[2], preparation_time);
        contentValues.put(Columns[3], stretch_time);
        contentValues.put(Columns[4], work_count);
        contentValues.put(Columns[5], work_time);
        contentValues.put(Columns[6], relax_count);
        contentValues.put(Columns[7], relax_time);
        contentValues.put(Columns[8], cycles_number);
        contentValues.put(Columns[9], sets_number);
        contentValues.put(Columns[10], relax_after_set_time);
        return contentValues;
    }
    public boolean insertData(String workout_name, int preparation_time, int stretch_time, int work_count, int work_time,
                              int relax_count, int relax_time, int cycles_number, int sets_number, int relax_after_set_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = createRow(workout_name, preparation_time, stretch_time, work_count, work_time, relax_count, relax_time, cycles_number, sets_number, relax_after_set_time);
        long insertedResult = db.insert(TABLE_NAME, null, contentValues);

        return insertedResult != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void updateData(int id, String workout_name, int preparation_time, int stretch_time,
                           int work_count, int work_time, int relax_count, int relax_time,
                           int cycles_number, int sets_number, int relax_after_set_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = createRow(workout_name, preparation_time, stretch_time, work_count, work_time, relax_count, relax_time, cycles_number, sets_number, relax_after_set_time);
        contentValues.put(Columns[0], id);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] { String.valueOf(id) });
    }

    public int deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[] {id });
    }
}
