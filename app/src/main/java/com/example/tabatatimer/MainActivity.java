package com.example.tabatatimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceManager;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    public static WorkoutDatabase workoutDatabase;
    public static float font_size;
    public static String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean theme_style = preferences.getBoolean("app_theme", false);
        if (theme_style) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        language = preferences.getString("language", "en");
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        font_size = Float.parseFloat(preferences.getString("font", "10"));
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.trainings);
        textView.setTextSize(font_size);

        workoutDatabase = Room.databaseBuilder(getApplicationContext(), WorkoutDatabase.class, "workoutdb").allowMainThreadQueries().build();

//        dbHelper = new DatabaseHelper(this);

//        Cursor cursor = dbHelper.getAllData();

        final List<WorkoutModel> trainings = workoutDatabase.workoutDao().getAll();
//        while (cursor.moveToNext()) {
//            trainings.add(new WorkoutModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
//                    cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6),
//                    cursor.getInt(7), cursor.getInt(8), cursor.getString(9), cursor.getString(10), cursor.getInt(11)));
//        }
        for (int i = 0; i < trainings.size(); i++) {
            WorkoutModel workoutModel = trainings.get(i);
            workoutModel.updateDescription();
            trainings.set(i, workoutModel);
        }
        final ListView listView = (ListView) findViewById(R.id.lvMain);


        listView.setAdapter(new CustomAdapter(trainings, this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), "Opening " + trainings.get(i).getWorkoutName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), SequenceActivity.class);
                intent.putExtra("SEQUENCE", trainings.get(i));
                startActivity(intent);
            }
        });


//        Workout workout = new Workout();
//        workout.setColor(trainings.get(0).getColor());
//        workout.setWorkoutName(trainings.get(0).getWorkout_name());
//        workout.setCyclesNumber(trainings.get(0).getCycles_number());
//        workout.setSetsNumber(trainings.get(0).getSets_number());
//        workout.setPreparationTime(trainings.get(0).getPreparation_time());
//        workout.setStretchTime(trainings.get(0).getStretch_time());
//        workout.setWorkTime(trainings.get(0).getWork_time());
//        workout.setRelaxTime(trainings.get(0).getRelax_time());
//        workout.setRelaxAfterSet(trainings.get(0).getRelax_after_set_time());
//        workout.setId(trainings.get(0).getId() + 5);
//        workout.workout_order = trainings.get(0).getWorkout_order();
//        workout.workout_order_time = trainings.get(0).getWorkout_order_time();
//        workoutDatabase.workoutDao().insert(workout);
//
//        List<Workout> train = workoutDatabase.workoutDao().getAll();
    }

    public void floatingButtonClick(View view) {
        Intent intent = new Intent(this, SequenceActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        menu.add(R.string.settings);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

}