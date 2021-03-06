package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    DrawImage drawImage;
    private ArrayList<Integer> poses = new ArrayList<>();
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private ArrayList<String> bitmapsConverted = new ArrayList<>();
    private ArrayList<Integer> pictNum = new ArrayList<>();
    DrawImageTask drawImageTask;
    Random rand = new Random();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        drawImage = findViewById(R.id.MainW);
        try {
            System.out.println(Arrays.toString(getAssets().list("Images/game")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           for(String fileName: getAssets().list("Images/game")){
               InputStream inputStream = getAssets().open("Images/game/" + fileName);
               Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
               bitmaps.add(bitmap);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bitmaps.size(); i++){
            pictNum.add(i, 0);
        }
        for (int i = 0; i <= 14; i++) {
            poses.add(i);
        }
        drawImageTask = new DrawImageTask();
        drawImageTask.execute();
    }


        public class DrawImageTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            int random_integer = rand.nextInt(4) +5;
            for (int i = 0; i < random_integer; i++) {
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
                pictNum.set(rand_bitm, pictNum.get(rand_bitm) + 1);

            }
            drawImage.invalidate();


        }


        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            drawImage.clearBitmaps();
            Intent j = new Intent(GameActivity.this, CheckActivity.class);
            j.putExtra("pict_num", pictNum);

            j.putExtra("bitmaps_converted", bitmapsConverted);
            startActivity(j);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        drawImageTask.cancel(true);

    }
}

