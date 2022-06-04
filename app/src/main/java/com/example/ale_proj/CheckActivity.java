package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {
    public static class Test {
        public Bitmap bitmap;
        public int answer = 0;
        public int trueAnswer;
    }

    Button checkButton;
    TextView textView;
    TextView textViewAnswer;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database database = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        textView = findViewById(R.id.test_text);
        textViewAnswer = findViewById(R.id.answer_write);
        editText = findViewById(R.id.name_enter);
        checkButton = findViewById(R.id.checkButton);
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        ArrayList<Integer> pictNum = getIntent().getIntegerArrayListExtra("pictNum");
        System.out.println(pictNum);
        try {
            for (String fileName : getAssets().list("Images/game")) {
                InputStream inputStream = getAssets().open("Images/game/" + fileName);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                bitmaps.add(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Test> tests = new ArrayList<>();
        int d = 0;
        for (Bitmap i : bitmaps) {
            System.out.println("fores");
            System.out.println(i);
            Test t = new Test();
            t.bitmap = i;
            t.trueAnswer = pictNum.get(d);
            tests.add(t);
            d++;
        }

        System.out.println(tests);
        System.out.println(bitmaps);

        ListView listView = findViewById(R.id.check_ListView);
        CheckAdapter checkAdapter = new CheckAdapter(this, tests);

        listView.setAdapter(checkAdapter);
        findViewById(R.id.checkButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println(tests.get(0).answer);
                int raz = 0;
                int truea = 0;
                for (int i = 0; i < pictNum.size(); i++) {
                    raz += Math.abs(tests.get(i).trueAnswer - tests.get(i).answer);
                    truea += tests.get(i).trueAnswer;
                }
                if (!editText.getText().toString().equals("")) {
//                    textView_answer.setBackgroundResource(R.color.black);
                    textViewAnswer.setVisibility(View.VISIBLE);
                    checkButton.setVisibility(View.GONE);
                    editText.setVisibility(View.GONE);
                    String ans = String.format("%.0f", (1 - (1.0f * raz / truea)) * 100);
                    if (Integer.parseInt(ans) <= 35){
                        textViewAnswer.setBackgroundResource(R.color.red);
                    }else if(Integer.parseInt(ans) >= 75){
                        textViewAnswer.setBackgroundResource(R.color.green);
                    }else{
                        textViewAnswer.setBackgroundResource(R.color.yellow);
                    }

                    textViewAnswer.setText(getString(R.string.yourResult) + ans + getString(R.string.matching));
                    database.insert(editText.getText().toString(), Integer.parseInt(ans));
                }
            }
        });
        listView.setAdapter(checkAdapter);
    }
}