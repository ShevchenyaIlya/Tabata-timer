package com.example.tabatatimer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    private ArrayList<WorkoutModel> items = new ArrayList<WorkoutModel>();

    public CustomAdapter(ArrayList<WorkoutModel> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public WorkoutModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.program_list_item, null);
        }

        WorkoutModel obj = items.get(position);
        if (obj != null) {
            TextView title = (TextView) view.findViewById(R.id.titleTextView);
            TextView description = (TextView) view.findViewById(R.id.descriptionTextView);
            if (title != null) {
                title.setText(obj.getWorkout_name());
            }
            if (description != null) {
                description.setText(obj.getDescription());
            }
//            view.setBackgroundColor(obj.getColor());
            GradientDrawable backgroundGradient = (GradientDrawable)view.getBackground();
            backgroundGradient.setColor(obj.getColor());
//            Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
//            String[] spinnerItem = new String[] {"Title", "Color"};
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerItem);;
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);

            ImageButton startProgram = (ImageButton) view.findViewById(R.id.startProgram);
            startProgram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            ImageView configButton = (ImageView) view.findViewById(R.id.configButton);
            configButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopup(view, position);
                }
            });
        }


        return view;
    }

    public void showPopup(final View v, final int position) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.first_item:
                        DatabaseHelper dbHelper = new DatabaseHelper(context);
                        getItem(position).deleteWorkout(dbHelper);
                        items.remove(position);
                        notifyDataSetChanged();
                        return true;
                    case R.id.second_item:
                        final WorkoutModel obj = items.get(position);
                        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(context, obj.getColor(), new AmbilWarnaDialog.OnAmbilWarnaListener() {
                            @Override
                            public void onCancel(AmbilWarnaDialog dialog) {

                            }

                            @Override
                            public void onOk(AmbilWarnaDialog dialog, int color) {
                                obj.setColor(color);
                                DatabaseHelper dbHelper = new DatabaseHelper(context);
                                obj.updateWorkout(dbHelper);
                                notifyDataSetChanged();
                            }
                        });
                        colorPicker.show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }
}
