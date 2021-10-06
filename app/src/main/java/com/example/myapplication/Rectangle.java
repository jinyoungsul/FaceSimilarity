package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class Rectangle extends View {
    Paint paint = new Paint();

    public Rectangle(Context context) {
        super(context);
    }


    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);

        int displayX = this.getWidth();
        int displayY = this.getHeight();
        int centerX = displayX / 2;
        int centerY = (displayY / 2) - 300;
        int border = 400;
        Log.d("초록색 그릴 때", "centerX"+centerX);
        Log.d("초록색 그릴 때", "centerY"+centerY);

        Log.d("초록색 그릴 때", "displayX : "+displayX);
        Log.d("초록색 그릴 때", "displayY : "+displayY);

        Rect rect = new Rect(centerX - border, centerY - border, centerX + border, centerY + border);

        Log.d("초록색 그릴 때", "rect : "+rect);
        canvas.drawRect(rect, paint );
    }
}
