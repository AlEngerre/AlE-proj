package com.example.ale_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
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

            for(int i = 0; i < 10; i++){

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
            drawImage.addBitmap(new Random().nextInt(14), BitmapFactory.decodeResource(getResources(), R.drawable.d1));
            drawImage.invalidate();

        }

    }
}

