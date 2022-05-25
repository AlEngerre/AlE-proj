package com.example.ale_proj;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public  class CircleView extends View {
    public CircleView (Context context, AttributeSet attrs) {
        super(context);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleView);
        typedArray.recycle();
    }







    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();

        paint.setColor(Color.RED);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.d1);
        canvas.drawBitmap(b, 0, 0, paint);

    }

}
