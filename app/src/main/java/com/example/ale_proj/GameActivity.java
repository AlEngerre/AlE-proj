package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private Integer prev_n = -1;
    private int Rd_n;
    DrawImage drawImage;
    private ArrayList<Integer> poses = new ArrayList<>();
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    int rd_int = -1;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        drawImage = findViewById(R.id.MainW);
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.d1));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.d2));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.d3));
        for (int i = 0; i <= 14; i++) {
            poses.add(i);
        }
        new DrawImageTask().execute();
    }

    class DrawImageTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            int random_integer = rand.nextInt(4) +5;
            for (int i = 1; i < random_integer; i++) {
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
            int bm_count = rand.nextInt(2) + 1;

            List<Integer> rand_ints = RandomSequence.getRandom(0, 14, bm_count);

            for (int i = 0; i < bm_count; i++) {
                drawImage.addBitmap(rand_ints.get(i), bitmaps.get(rand.nextInt(bitmaps.size())));
            }
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
            j.putExtra("text", test);
            startActivity(j);
        }
    }
}

