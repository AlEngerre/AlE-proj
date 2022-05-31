package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class CheckActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_activity);
        textView = findViewById(R.id.test_text);
        String text = getIntent().getStringExtra("text");
        textView.setText(text);
    }
}