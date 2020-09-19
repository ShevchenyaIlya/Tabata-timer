package com.example.tabatatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    String[] title = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    String[] description = { "Иван, asdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfgg", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);

        ListView listView = (ListView) findViewById(R.id.lvMain);


        ArrayList<DataModel> items = new ArrayList<DataModel>();
        for (int i = 0; i  < 7; i++) {
            items.add(new DataModel(title[i], description[i]));
        }

        listView.setAdapter(new CustomAdapter(items, this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
//                setTimeDialog(i);
            }
        });

    }

    public void floatingButtonClick(View view) {
//        Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
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