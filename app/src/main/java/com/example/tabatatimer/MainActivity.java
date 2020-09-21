package com.example.tabatatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);

        Cursor cursor = dbHelper.getAllData();
        final ArrayList<WorkoutModel> trainings = new ArrayList<WorkoutModel>();
        while (cursor.moveToNext()) {
            trainings.add(new WorkoutModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                    cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6),
                    cursor.getInt(7), cursor.getInt(8), cursor.getString(9), cursor.getString(10)));
        }

        final ListView listView = (ListView) findViewById(R.id.lvMain);


        listView.setAdapter(new CustomAdapter(trainings, this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), "Opening " + trainings.get(i).getWorkout_name(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), SequenceActivity.class);
                intent.putExtra("SEQUENCE", trainings.get(i));
                startActivity(intent);
            }
        });

    }

    public void floatingButtonClick(View view) {
        Intent intent = new Intent(this, SequenceActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        menu.add("Settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

//        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
//        CharSequence title = item.getTitle();
//        setWidgetsConfigurations(title);
        return super.onOptionsItemSelected(item);
    }

//    protected void listItemOnClickHandler(@NotNull View view) {
//        LinearLayout parentRow = (LinearLayout)view.getParent();
//        ImageButton btnClick = (ImageButton) parentRow.getChildAt(0);
//        parentRow.setBackgroundColor(Color.CYAN);
//        parentRow.refreshDrawableState();
//
//    }
}