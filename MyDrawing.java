package com.example.drawingexam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class MyDrawing extends View {
    public MyDrawing(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setColor(Color.RED);
        canvas.drawLine(10,50,700,50,paint);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        canvas.drawLine(10,80,700,80,paint);

        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(20);
        canvas.drawLine(10,50,700,50,paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);

        paint.setStyle(Paint.Style.FILL);
        Rect rect1 = new Rect(10, 100, 10+300, 100+300);
        canvas.drawRect(rect1, paint);

        paint.setStyle(Paint.Style.STROKE);
        Rect rect2 = new Rect(330, 160, 330+320, 160+320);
        canvas.drawRect(rect1, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        RectF rect3 = new RectF(670, 160, 670+250, 160+250);
        canvas.drawRoundRect(rect3, 10 ,10,paint);

        canvas.drawCircle(100,600,80,paint);

        paint.setColor(Color.BLUE);
        RectF rect4 = new RectF(250,500,750,680);
        canvas.drawOval(rect4, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        RectF rect5 = new RectF(800,500,1200,680);
        canvas.drawArc(rect5, 100, 90, false, paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);

        paint.setStyle(Paint.Style.STROKE);
        Path path1 = new Path();
        path1.moveTo(50,800);
        path1.lineTo(50+100,800+100);
        path1.lineTo(50+200,800);
        path1.lineTo(50+300,800+100);
        path1.lineTo(50+400,800);
        canvas.drawPath(path1, paint);

        paint.setStrokeWidth(5);
        paint.setTextSize(70);
        paint.setColor(Color.GREEN);
        canvas.drawText("안드로이드",50,1000, paint);
    }
}
