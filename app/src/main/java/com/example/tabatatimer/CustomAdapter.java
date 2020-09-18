package com.example.tabatatimer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    private ArrayList<DataModel> items = new ArrayList<DataModel>();

    public CustomAdapter(ArrayList<DataModel> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public DataModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.program_list_item, null);
        }

        DataModel obj = items.get(position);
        if (obj != null) {
            TextView title = (TextView) view.findViewById(R.id.titleTextView);
            TextView description = (TextView) view.findViewById(R.id.descriptionTextView);
            if (title != null) {
                title.setText(obj.title);
            }
            if (description != null) {
                description.setText(obj.description);
            }
//            Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
//            String[] spinnerItem = new String[] {"Title", "Color"};
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerItem);;
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
        }


        ImageButton startProgram = (ImageButton) view.findViewById(R.id.startProgram);
        startProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = (LinearLayout)view.getParent();
                LinearLayout parent = (LinearLayout)layout.getParent();
                GradientDrawable bgShape = (GradientDrawable)parent.getBackground();
                bgShape.setColor(Color.RED);
            }
        });


        return view;
    }

}
