package com.example.tabatatimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "WorkoutDatabase.db";
    private static final int version = 1;
    private static final String TABLE_NAME_TRAINING = "training";
    private static final String[] Columns = new String[] {"id", "workout_name", "preparation_time", "stretch_time",
            "work_time", "relax_time", "cycles_number", "sets_number", "relax_after_set_time", "workout_order", "workout_order_time"};


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = createMainTableCommand();
        sqLiteDatabase.execSQL(createTable);
    }

    private String createMainTableCommand() {
        String createTable = "CREATE TABLE " + TABLE_NAME_TRAINING + " (id INTEGER PRIMARY KEY AUTOINCREMENT, workout_name TEXT, ";
        for (String value: Columns) {
            if (!value.equals("id") && !value.equals("workout_name") && !value.equals("workout_order") && !value.equals("workout_order_time")) {
                String insertColumn = value + " INTEGER, ";
                createTable = createTable + insertColumn;
            }
        }
        createTable += "workout_order TEXT, workout_order_time TEXT)";

        return createTable;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRAINING);
        onCreate(sqLiteDatabase);
    }

    protected ContentValues createRow(String workout_name, int preparation_time, int stretch_time, int work_time,
                                      int relax_time, int cycles_number, int sets_number, int relax_after_set_time, String workout_order, String workout_order_time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns[1], workout_name);
        contentValues.put(Columns[2], preparation_time);
        contentValues.put(Columns[3], stretch_time);
        contentValues.put(Columns[4], work_time);
        contentValues.put(Columns[5], relax_time);
        contentValues.put(Columns[6], cycles_number);
        contentValues.put(Columns[7], sets_number);
        contentValues.put(Columns[8], relax_after_set_time);
        contentValues.put(Columns[9], workout_order);
        contentValues.put(Columns[10], workout_order_time);
        return contentValues;
    }

    public boolean insertData(String workout_name, int preparation_time, int stretch_time, int work_time,
                              int relax_time, int cycles_number, int sets_number, int relax_after_set_time, String workout_order, String workout_order_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = createRow(workout_name, preparation_time, stretch_time, work_time, relax_time, cycles_number, sets_number, relax_after_set_time, workout_order, workout_order_time);
//        String Query = "Select * from " + TABLE_NAME_TRAINING + " where workout_name + " = " + ;
//        Cursor cursor = sqldb.rawQuery(Query, null);
//        if(cursor.getCount() <= 0){
//            cursor.close();
//            return false;
//        }
//        cursor.close();
//        Cursor cursor = db.rawQuery("SELECT id, workout_name FROM " + TABLE_NAME_TRAINING + " WHERE workout_name=" + workout_name, null);
//        cursor.moveToNext();
        long insertedResult = db.insert(TABLE_NAME_TRAINING, null, contentValues);
        return insertedResult != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME_TRAINING, null);
    }

    public void updateData(int id, String workout_name, int preparation_time, int stretch_time,
                           int work_time, int relax_time, int cycles_number, int sets_number, int relax_after_set_time,
                           String workout_order, String workout_order_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = createRow(workout_name, preparation_time, stretch_time, work_time, relax_time, cycles_number, sets_number, relax_after_set_time, workout_order, workout_order_time);
        contentValues.put(Columns[0], id);
        db.update(TABLE_NAME_TRAINING, contentValues, "id = ?", new String[] { String.valueOf(id) });
    }

    public int deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_TRAINING, "id = ?", new String[] {id });
    }


}
