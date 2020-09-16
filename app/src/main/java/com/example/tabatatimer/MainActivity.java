package com.example.tabatatimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public class Obj {
        public String title;
        public String description;
        public Obj(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }

    public class ObjAdapter extends ArrayAdapter<Obj> {

        private Context context;
        private ArrayList<Obj> items;

        public ObjAdapter(Context context, int layoutResId, ArrayList<Obj> data) {
            super(context, layoutResId, data);
            this.context = context;
            this.items = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.custom_list_item, null);
            }
            Obj o = items.get(position);
            if (o != null) {
                TextView title = (TextView) v.findViewById(R.id.titleTextView);
                TextView description = (TextView) v.findViewById(R.id.descriptionTextView);
                if (title != null) {
                    title.setText(o.title);
                }
                if (description != null) {
                    description.setText(o.description);
                }
            }
            return v;
        }

    }
    String[] title = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    String[] description = { "Иван, asdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfggasdgfadgfhfghsafdasdgdafgdfgdfgfdsgsdgfdgsdfgsdfgdfgdsfgdsfgg", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lvMain);

        ArrayList<Obj> items = new ArrayList<Obj>();
        for (int i = 0; i  < 7; i++) {
            items.add(new Obj(title[i], description[i]));
        }

        listView.setAdapter(new ObjAdapter(this, R.layout.custom_list_item, items));
    }
}