package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        System.out.println("zxc");
        System.out.println(pict_num);
    }
}