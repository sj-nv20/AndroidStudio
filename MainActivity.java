package com.example.drawingexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1;
    final static int CIRCLE = 2;
    final static int RECT = 3;
    static int current_shape_ = LINE;
    static int current_color_ = Color.RED;
    static Paint.Style current_style_ = Paint.Style.STROKE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyDrawing(this));
        setTitle("그림판");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"선 그리기");
        menu.add(0,2,0,"원 그리기");
        SubMenu sub_menu1_ = menu.addSubMenu("사각형 그리기");
        sub_menu1_.add(0,3,0,"fill");
        sub_menu1_.add(0,7,0,"stroke");

        SubMenu sub_menu_ = menu.addSubMenu("색상 선택");
        sub_menu_.add(0,4,0,"Red");
        sub_menu_.add(0,5,0,"Blue");
        sub_menu_.add(0,6,0,"Green");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                current_shape_ = LINE;
                return true;
            case 2:
                current_shape_ = CIRCLE;
                return true;
            case 3:
                current_shape_ = RECT;
                current_style_ = Paint.Style.FILL;
                return true;
            case 4:
                current_color_ = Color.RED;
                return true;
            case 5:
                current_color_ = Color.BLUE;
                return true;
            case 6:
                current_color_ = Color.GREEN;
                return true;
            case 7:
                current_shape_ = RECT;
                current_style_ = Paint.Style.STROKE;
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    private static class DrawingView extends View {
        int startX_ = -1;
        int startY_ = -1;
        int stopX_ = -1;
        int stopY_ = -1;
        
        public DrawingView(Context context) {
            super(context);
        }
        
        @Override
        public  boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX_ = (int)event.getX();
                    startY_ = (int)event.getY();
                    break;
                    case MotionEvent.ACTION_MOVE:
                        case MotionEvent.ACTION_UP:
                            stopX_ = (int)event.getX();
                            stopY_ = (int)event.getY();
                            this.invalidate();
                            break;

            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(current_style_);
            paint.setColor(current_color_);

            switch (current_shape_) {
                case LINE:
                    canvas.drawLine(startX_, startY_, stopX_, stopY_, paint);
                    break;
                case CIRCLE:
                    int radius = (int)Math.sqrt(Math.pow(stopX_-startX_, 2)+Math.pow(stopY_ - startY_, 2));
                    canvas.drawCircle(startX_,startY_,radius, paint);
                    break;
                case RECT:
                    canvas.drawRect(startX_,startY_,stopX_,stopY_,paint);
                    break;
            }
        }
    }
}