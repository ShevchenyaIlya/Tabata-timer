package com.example.tabatatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] title = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    String[] description = { "Иван, asdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfgg", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lvMain);
//
//        ArrayList<SequenceModel> items = new ArrayList<SequenceModel>();
//        items.add(new SequenceModel(R.drawable.directions_walk, "Подготовка", "24"));
//        items.add(new SequenceModel(R.drawable.directions_run, "Разминка", "12"));
//        items.add(new SequenceModel(R.drawable.access_alarm, "Работа", "26"));
//        items.add(new SequenceModel(R.drawable.accessibility, "Отдых", "30"));
//        items.add(new SequenceModel(R.drawable.repeat, "Циклы", "50"));
//        items.add(new SequenceModel(R.drawable.update, "Сеты", "50"));
//        items.add(new SequenceModel(R.drawable.weekend, "Отдых между сетами", "50"));
//        listView.setAdapter(new SequenceAdapter(items, this));
        ArrayList<DataModel> items = new ArrayList<DataModel>();
        for (int i = 0; i  < 7; i++) {
            items.add(new DataModel(title[i], description[i]));
        }

        listView.setAdapter(new CustomAdapter(items, this));

        FloatingActionButton fab = findViewById(R.id.tab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

//    protected void listItemOnClickHandler(@NotNull View view) {
//        LinearLayout parentRow = (LinearLayout)view.getParent();
//        ImageButton btnClick = (ImageButton) parentRow.getChildAt(0);
//        parentRow.setBackgroundColor(Color.CYAN);
//        parentRow.refreshDrawableState();
//
//    }
}