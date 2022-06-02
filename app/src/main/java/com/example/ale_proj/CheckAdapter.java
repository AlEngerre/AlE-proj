package com.example.ale_proj;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckAdapter extends ArrayAdapter<CheckActivity.Test> {
    public CheckAdapter(Context context, ArrayList<CheckActivity.Test> bitmaps){
        super(context, R.layout.check_adapter_item, bitmaps);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final CheckActivity.Test test = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.check_adapter_item, null);
        }

// Заполняем адаптер

        ((ImageView) convertView.findViewById(R.id.picture_IV)).setImageBitmap(test.bitmap);
        TextView textview = ((TextView) convertView.findViewById(R.id.amount_TV));
        ((Button) convertView.findViewById(R.id.del)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.answer -= 1;
                textview.setText(String.valueOf(test.answer));
            }
        });
        ((Button) convertView.findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.answer += 1;
                textview.setText(String.valueOf(test.answer));
            }
        });
        return convertView;
    }
}
