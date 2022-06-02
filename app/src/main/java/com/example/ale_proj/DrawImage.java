package com.example.ale_proj;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public  class DrawImage extends View{
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private ArrayList<Integer> positions = new ArrayList<>();
    public DrawImage(Context context, AttributeSet attrs) {
        super(context, attrs);

//        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DrawImage);
//        typedArray.recycle();
    }

    public void addBitmap(int pos, Bitmap bitmap){
        bitmaps.add(bitmap);
        positions.add(pos);
    }


    public void clearBitmaps(){
        bitmaps.clear();
        positions.clear();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 3;
        int height = getHeight() / 5;
        for (int i = 0; i < positions.size(); i++){
            int x = (positions.get(i) % 3) * width;
            int y = (positions.get(i) / 3) * height;
            canvas.drawBitmap(
                    bitmaps.get(i),
                    new Rect(0, 0, bitmaps.get(i).getWidth(), bitmaps.get(i).getHeight()),
                    new Rect(x, y, x + width, y + height),
                    new Paint());
        }
    }

    }






