package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public Integer prev_n = -1;
    public int Rd_n;
    DrawImage drawImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        drawImage = findViewById(R.id.MainW);
        System.out.println(drawImage);
        new DrawImageTask().execute();
    }

    class DrawImageTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = -1; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            drawImage.clearBitmaps();
            do{
                Rd_n = new Random().nextInt(14);
                System.out.println(Rd_n);
            }while (prev_n.compareTo(Rd_n) == 0 && prev_n > 0);
            prev_n = Rd_n;
            drawImage.addBitmap(Rd_n, BitmapFactory.decodeResource(getResources(), R.drawable.d1));
            drawImage.invalidate();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            drawImage.clearBitmaps();
            Intent j = new Intent(GameActivity.this, CheckActivity.class);
            String test = "asd";
            j.putExtra("text",test);
            startActivity(j);
        }
    }
}

