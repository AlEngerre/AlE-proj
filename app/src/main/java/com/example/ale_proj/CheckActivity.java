package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_check);
        textView = findViewById(R.id.test_text);
        ArrayList<Integer> pict_num = getIntent().getIntegerArrayListExtra("pict_num");
        ArrayList<Integer> bitmaps_converted = getIntent().getIntegerArrayListExtra("bitmaps_converted");
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        System.out.println("zxc");

        for (Integer i: bitmaps_converted) {
            bitmaps.add((BitmapFactory.decodeResource(getResources(), i)));
        }

        System.out.println(bitmaps_converted);
    }
}