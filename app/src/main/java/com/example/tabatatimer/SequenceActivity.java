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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_sequence);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ListView listView = (ListView) findViewById(R.id.sequenceListView);
        workout = (WorkoutModel) getIntent().getSerializableExtra("SEQUENCE");
        items = new ArrayList<SequenceControllerModel>();
        TextView text = (TextView) findViewById(R.id.training_name);
        ArrayList<Integer> baseValues;
        if (workout == null) {
            baseValues = new ArrayList<Integer>(Arrays.asList(30, 30, 30, 10, 4, 1, 60));
            workout = new WorkoutModel(0, "Base name", 30, 30, 30, 10, 4, 1, 60, "", "");
        }
        else {
            baseValues = new ArrayList<Integer>(Arrays.asList(workout.getPreparation_time(), workout.getStretch_time(),
                    workout.getWork_time(), workout.getRelax_time(), workout.getCycles_number(),
                    workout.getSets_number(), workout.getRelax_after_set_time()));
            text.setText(workout.getWorkout_name());
        }

        items.add(new SequenceControllerModel(R.drawable.directions_walk, "Подготовка", baseValues.get(0)));
        items.add(new SequenceControllerModel(R.drawable.directions_run, "Разминка", baseValues.get(1)));
        items.add(new SequenceControllerModel(R.drawable.access_alarm, "Работа", baseValues.get(2)));
        items.add(new SequenceControllerModel(R.drawable.accessibility, "Отдых", baseValues.get(3)));
        items.add(new SequenceControllerModel(R.drawable.repeat, "Циклы", baseValues.get(4)));
        items.add(new SequenceControllerModel(R.drawable.update, "Сеты", baseValues.get(5)));
        items.add(new SequenceControllerModel(R.drawable.weekend, "Отдых между сетами", baseValues.get(6)));

        listView.setAdapter(new SequenceAdapter(items, workout, this, ""));
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
        workout.setPreparation_time(items.get(0).getBaseValue());
        workout.setStretch_time(items.get(1).getBaseValue());
        workout.setWork_time(items.get(2).getBaseValue());
        workout.setRelax_time(items.get(3).getBaseValue());
        workout.setCycles_number(items.get(4).getBaseValue());
        workout.setSets_number(items.get(5).getBaseValue());
        workout.setRelax_after_set_time(items.get(6).getBaseValue());
        workout.updateWorkingOrder();

        intent.putExtra("SEQUENCE", workout);
        startActivity(intent);
    }
}
