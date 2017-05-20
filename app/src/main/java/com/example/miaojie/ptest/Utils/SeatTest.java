package com.example.miaojie.ptest.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.miaojie.ptest.R;

/**
 * Created by miaojie on 2017/3/22.
 */

public class SeatTest extends View {
    private Matrix matrix=new Matrix();
    private Paint paint;
    private Bitmap bitmap;
    private int DownX,DownY,LastX,LastY;
    private boolean juge=false;
    public SeatTest(Context context) {
        super(context);
    }

    public SeatTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.rate);
        matrix.postTranslate(100,100);
        DownY=0;
        DownX=0;

    }

    public SeatTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,matrix,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();
        float NowX=0,NowY=0;
        int x= (int) event.getX();
        int y= (int) event.getY();
        super.onTouchEvent(event);
        if(event.getPointerCount()>1)
            juge=true;
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                DownX=x;
                DownY=y;
                juge=false;
                Log.e("asd","asd");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("asd","dsa");
                int downDX = Math.abs(x - DownX);
                int downDY = Math.abs(y - DownY);
                if ((downDX > 10 || downDY > 10) && !juge) {
                    int dx = x - LastX;
                    int dy = y - LastY;
                    matrix.postTranslate(dx, dy);
                    invalidate();
                }
                invalidate();
                break;
        }
        LastX=x;
        LastY=y;
       return true;
    }
}
