package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {
    public static class Test{
        public Bitmap bitmap;
        public int answer = 0;
        public int trueAnswer;
    }
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_check);
        textView = findViewById(R.id.test_text);
        ArrayList<Integer> pict_num = getIntent().getIntegerArrayListExtra("pict_num");
        ArrayList<Integer> bitmaps_converted = getIntent().getIntegerArrayListExtra("bitmaps_converted");

        ArrayList<Test> tests =  new ArrayList<>();
        int d = 0;

        for (Integer i: bitmaps_converted) {
            Test t = new Test();
            t.bitmap = BitmapFactory.decodeResource(getResources(), i);
            t.trueAnswer = pict_num.get(d);
            tests.add(t);
            d++;
        }

        System.out.println(tests);
        System.out.println(bitmaps_converted);

        ListView listView = findViewById(R.id.check_ListView);
        CheckAdapter checkAdapter = new CheckAdapter(this, tests);

        listView.setAdapter(checkAdapter);
        findViewById(R.id.check_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(tests.get(0).answer);
                int user = 0;
                int truea = 0;
                for (int i = 0; i < pict_num.size(); i++){
                    user += tests.get(i).answer;
                    truea += tests.get(i).trueAnswer;
                }float result = 0;
                result = 1.0f * user / truea;
                System.out.println(result);
            }
        });
        listView.setAdapter(checkAdapter);
    }
}