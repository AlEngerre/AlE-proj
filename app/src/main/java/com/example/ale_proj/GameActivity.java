package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private Integer prev_n = -1;
    private int Rd_n;
    DrawImage drawImage;
    private ArrayList<Integer> poses = new ArrayList<>();
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private ArrayList<Integer> bitmaps_converted = new ArrayList<>();
    private ArrayList<Integer> pict_num = new ArrayList<>();
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        pict_num.add(0,0);
        pict_num.add(1, 0);
        pict_num.add(2, 0);
        drawImage = findViewById(R.id.MainW);
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.d1));
        bitmaps_converted.add(R.drawable.d1);
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.d2));
        bitmaps_converted.add(R.drawable.d2);
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.d3));
        bitmaps_converted.add(R.drawable.d3);
        for (int i = 0; i <= 14; i++) {
            poses.add(i);
        }
        new DrawImageTask().execute();
    }

    class DrawImageTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            int random_integer = rand.nextInt(4) +5;
            for (int i = 0; i < random_integer; i++) {
                System.out.println(i);
                publishProgress();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                drawImage.clearBitmaps();
                drawImage.invalidate();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            drawImage.clearBitmaps();
            int bm_count = rand.nextInt(3) + 1;

            List<Integer> rand_ints = RandomSequence.getRandom(0, 14, bm_count);

            for (int i = 0; i < bm_count; i++) {
                int rand_bitm = rand.nextInt(bitmaps.size());
                drawImage.addBitmap(rand_ints.get(i), bitmaps.get(rand_bitm));
                pict_num.set(rand_bitm, pict_num.get(rand_bitm) + 1);

            }
            System.out.println(pict_num);
            drawImage.invalidate();


        }


        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            drawImage.clearBitmaps();
            System.out.println(pict_num);
            System.out.println("");
            Intent j = new Intent(GameActivity.this, CheckActivity.class);
            j.putExtra("pict_num", pict_num);

            j.putExtra("bitmaps_converted", bitmaps_converted);
            startActivity(j);
        }
    }
}

