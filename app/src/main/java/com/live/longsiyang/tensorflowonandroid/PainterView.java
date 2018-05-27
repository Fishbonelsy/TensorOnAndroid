package com.live.longsiyang.tensorflowonandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.live.longsiyang.tensorflowonandroid.bitmapUtils.BitmapUtils;

public class PainterView extends View {

    private float mPreX;
    private float mPreY;
    private Path mPath;
    private Paint mPaint;

    public PainterView(Context context) {
        super(context);
        init();
    }

    public PainterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PainterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(30);
        mPaint.setAntiAlias(true);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case  MotionEvent.ACTION_MOVE:
                float endX = (mPreX+event.getX())/2;
                float endY = (mPreY+event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mPath,mPaint);
    }

    public void clearPath(){
        mPath.reset();
        invalidate();
    }

    public Bitmap createBitmap(){

        Bitmap bitmap = BitmapUtils.loadBitmapFromView(this);
        return bitmap;
    }


}