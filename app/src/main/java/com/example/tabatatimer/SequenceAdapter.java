package com.example.tabatatimer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SequenceAdapter extends ArrayAdapter<SequenceModel> implements View.OnClickListener{

    private Context context;
    private ArrayList<SequenceModel> rowElements = new ArrayList<SequenceModel>();

    public SequenceAdapter(ArrayList<SequenceModel> items, Context context) {
        super(context, R.layout.create_sequence_list_item, items);
        this.context = context;
        this.rowElements = items;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.create_sequence_list_item, null);
        }

        final SequenceModel rowData = getItem(position);

        if (rowData != null) {
            TextView title = (TextView) view.findViewById(R.id.sequenceItemTitle);
            ImageView image = (ImageView) view.findViewById(R.id.sequenceItemImage);
            final EditText value = (EditText) view.findViewById(R.id.sequenceItemValue);

            if (title != null) {
                title.setText(rowData.getTitle());
            }

            if (value != null) {
                value.setText(rowData.getBaseValue().toString());
            }

            if (image != null) {
                image.setImageResource(rowData.getActivityImage());
            }

            ImageView addValue = (ImageView) view.findViewById(R.id.addValue);
            addValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int number = Integer.parseInt(value.getText().toString());
                    rowData.setBaseValue(String.valueOf(number + 1));
                    value.setText(String.valueOf(number + 1));
                }
            });

            ImageView deleteValue = (ImageView) view.findViewById(R.id.deleteValue);
            deleteValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int number = Integer.parseInt(value.getText().toString());
                    rowData.setBaseValue(String.valueOf(number - 1));
                    value.setText(String.valueOf(number - 1));
                }
            });

            value.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    rowData.setBaseValue(value.getText().toString());
                }
            });
//            Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
//            String[] spinnerItem = new String[] {"Title", "Color"};
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerItem);;
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
        }


//        ImageButton startProgram = (ImageButton) view.findViewById(R.id.startProgram);
//        startProgram.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LinearLayout layout = (LinearLayout)view.getParent();
//                LinearLayout parent = (LinearLayout)layout.getParent();
//                GradientDrawable bgShape = (GradientDrawable)parent.getBackground();
//                bgShape.setColor(Color.RED);
//            }
//        });
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
