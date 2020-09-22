package com.example.tabatatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class EditSequenceActivity extends AppCompatActivity {
    ArrayList<SequenceControllerModel> items_order;
    private WorkoutModel workout;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sequence);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        dbHelper = new DatabaseHelper(this);
        ListView listView = (ListView) findViewById(R.id.editSequenceListView);
        workout = (WorkoutModel) getIntent().getSerializableExtra("SEQUENCE");


        EditText editText = (EditText) findViewById(R.id.editPageTextView);
        editText.setText(workout.getWorkout_name());

        items_order = new ArrayList<SequenceControllerModel>();

        ArrayList<String> fazes = workout.getWorkout_order();
        ArrayList<Integer> fazes_time = workout.getWorkout_order_time();
        for(int i = 0; i < fazes.size(); i++) {
            int imageId = 0;
            String faze = fazes.get(i);
            switch (faze) {
                case "Work": imageId = R.drawable.access_alarm; break;
                case "Rest": imageId = R.drawable.accessibility; break;
                case "Preparation": imageId = R.drawable.directions_walk; break;
                case "Stretch": imageId = R.drawable.directions_run; break;
                case "SetsRest": imageId = R.drawable.weekend; break;
                default: break;
            }

            items_order.add(new SequenceControllerModel(imageId, faze, fazes_time.get(i)));
        }
        listView.setAdapter(new SequenceAdapter(items_order, workout, this, ""));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SequenceActivity.class);
        intent.putExtra("SEQUENCE", workout);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
    public void saveChangesButton(View view) {
        ArrayList<String> fazes = workout.getWorkout_order();
        ArrayList<Integer> fazes_time = workout.getWorkout_order_time();
        EditText editText = (EditText)findViewById(R.id.editPageTextView);
        workout.setWorkout_name(editText.getText().toString());
        workout.updateDescription();
//        workout.setColor();

        for (int i = 0; i < fazes.size(); i++) {
            fazes.set(i, items_order.get(i).getTitle());
            fazes_time.set(i, items_order.get(i).getBaseValue());
        }

        if (workout.getId() != 0)
            workout.updateWorkout(dbHelper);
        else {
            boolean result = workout.saveWorkout(dbHelper);
            Toast.makeText(this, "Sequence with such name exist", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}