package com.example.tabatatimer;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {WorkoutModel.class}, version = 1, exportSchema = false)
@TypeConverters({TypeConverterStrings.class, TypeConverterInteger.class})
public abstract class WorkoutDatabase extends RoomDatabase {
    public abstract WorkoutDao workoutDao();
}


