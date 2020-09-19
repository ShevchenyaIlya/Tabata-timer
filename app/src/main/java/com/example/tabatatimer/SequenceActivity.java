package com.example.tabatatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;

public class SequenceActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_sequence);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ListView listView = (ListView) findViewById(R.id.sequenceListView);

        ArrayList<SequenceControllerModel> items = new ArrayList<SequenceControllerModel>();
        items.add(new SequenceControllerModel(R.drawable.directions_walk, "Подготовка", "24"));
        items.add(new SequenceControllerModel(R.drawable.directions_run, "Разминка", "12"));
        items.add(new SequenceControllerModel(R.drawable.access_alarm, "Работа", "26"));
        items.add(new SequenceControllerModel(R.drawable.accessibility, "Отдых", "30"));
        items.add(new SequenceControllerModel(R.drawable.repeat, "Циклы", "50"));
        items.add(new SequenceControllerModel(R.drawable.update, "Сеты", "50"));
        items.add(new SequenceControllerModel(R.drawable.weekend, "Отдых между сетами", "50"));
        listView.setAdapter(new SequenceAdapter(items, this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), String.valueOf(i), Toast.LENGTH_LONG).show();
//                setTimeDialog(i);
            }
        });

    }

    private void setTimeDialog(int itemIndex) {
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.set_time_dialog, null);
        NumberPicker numberPickerMinutes = (NumberPicker) linearLayout.findViewById(R.id.numberPicker1);
        NumberPicker numberPickerSeconds = (NumberPicker) linearLayout.findViewById(R.id.numberPicker2);

        numberPickerMinutes.setMinValue(0);
        numberPickerMinutes.setMaxValue(30);
        numberPickerMinutes.setValue(1);
        numberPickerSeconds.setMinValue(0);
        numberPickerSeconds.setMaxValue(59);
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setPositiveButton("Submit", null)
                .setNegativeButton("Cancel", null)
                .setView(linearLayout)
                .setCancelable(false)
                .create();
        builder.show();

        //Setting up OnClickListener on positive button of AlertDialog
        builder.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code on submit
            }
        });
    }
}
