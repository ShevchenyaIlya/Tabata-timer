package com.example.tabatatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class SequenceActivity extends AppCompatActivity{

    WorkoutModel workout;
    ArrayList<SequenceControllerModel> items;
    public static boolean isCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_sequence);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        workout = (WorkoutModel) getIntent().getSerializableExtra("SEQUENCE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = (ListView) findViewById(R.id.sequenceListView);
        items = new ArrayList<SequenceControllerModel>();
        TextView text = (TextView) findViewById(R.id.training_name);
        text.setTextSize(MainActivity.font_size);
        ArrayList<Integer> baseValues;
        if (workout == null) {
            baseValues = new ArrayList<Integer>(Arrays.asList(30, 30, 30, 10, 4, 1, 60));
            isCreated = false;
            workout = new WorkoutModel().createWorkoutModel(MainActivity.workoutDatabase.workoutDao().getCount(), "Base name", 30, 30, 30, 10, 4, 1, 60, "", "", R.color.listItemColor);
        } else {
            baseValues = new ArrayList<Integer>(Arrays.asList(workout.getPreparationTime(), workout.getStretchTime(),
                    workout.getWorkTime(), workout.getRelaxTime(), workout.getCyclesNumber(),
                    workout.getSetsNumber(), workout.getRelaxAfterSetTime()));
            text.setText(workout.getWorkoutName());
            isCreated = true;
        }

        items.add(new SequenceControllerModel(R.drawable.directions_walk, getString(R.string.preparation), baseValues.get(0)));
        items.add(new SequenceControllerModel(R.drawable.directions_run, getString(R.string.stretch), baseValues.get(1)));
        items.add(new SequenceControllerModel(R.drawable.access_alarm, getString(R.string.work), baseValues.get(2)));
        items.add(new SequenceControllerModel(R.drawable.accessibility, getString(R.string.rest), baseValues.get(3)));
        items.add(new SequenceControllerModel(R.drawable.repeat, getString(R.string.cycle), baseValues.get(4)));
        items.add(new SequenceControllerModel(R.drawable.update, getString(R.string.sets), baseValues.get(5)));
        items.add(new SequenceControllerModel(R.drawable.weekend, getString(R.string.sets_rest), baseValues.get(6)));

        listView.setAdapter(new SequenceAdapter(items, workout, this, ""));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        workout = (WorkoutModel) data.getSerializableExtra("SEQUENCE");
    }

//    private void setTimeDialog(int itemIndex) {
//        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.set_time_dialog, null);
//        NumberPicker numberPickerMinutes = (NumberPicker) linearLayout.findViewById(R.id.numberPicker1);
//        NumberPicker numberPickerSeconds = (NumberPicker) linearLayout.findViewById(R.id.numberPicker2);
//
//        numberPickerMinutes.setMinValue(0);
//        numberPickerMinutes.setMaxValue(30);
//        numberPickerMinutes.setValue(1);
//        numberPickerSeconds.setMinValue(0);
//        numberPickerSeconds.setMaxValue(59);
//        final AlertDialog builder = new AlertDialog.Builder(this)
//                .setPositiveButton("Submit", null)
//                .setNegativeButton("Cancel", null)
//                .setView(linearLayout)
//                .setCancelable(false)
//                .create();
//        builder.show();
//
//        //Setting up OnClickListener on positive button of AlertDialog
//        builder.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Code on submit
//            }
//        });
//    }

    public void toListOfSequences(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toEditSequencePage(View view) {
        Intent intent = new Intent(this, EditSequenceActivity.class);
        workout.setPreparationTime(items.get(0).getBaseValue());
        workout.setStretchTime(items.get(1).getBaseValue());
        workout.setWorkTime(items.get(2).getBaseValue());
        workout.setRelaxTime(items.get(3).getBaseValue());
        workout.setCyclesNumber(items.get(4).getBaseValue());
        workout.setSetsNumber(items.get(5).getBaseValue());
        workout.setRelaxAfterSetTime(items.get(6).getBaseValue());
        workout.updateWorkingOrder();

        intent.putExtra("SEQUENCE", workout);
        startActivityForResult(intent, 1);
    }

    public void startTimer(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra("SEQUENCE", workout);
        startActivity(intent);
    }
}
