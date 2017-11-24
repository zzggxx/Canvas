package com.example.will.canvas.canvas_base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by will on 2017/11/24.
 */

public class SloopView extends View {

    private Paint mPaint;
    private int mBlack = 0xffff0000;
    private int mGreen = 0xff00ff00;

    public SloopView(Context context) {
        this(context, null);
    }

    public SloopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SloopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mBlack);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * draw point
         */
        canvas.drawPoint(
                100, 100,       //one point contain two coordinate x and y;
                mPaint);
        canvas.drawPoints(new float[]{
                        100, 200,
                        100, 300,
                        100, 250,
                        100, 350},
                mPaint);

        /**
         * draw line
         */
        canvas.drawLine(
                150, 250, 150, 600,     //left,top,right,bottom
                mPaint);
        canvas.drawLines(new float[]{
                        350, 250, 300, 600,     //if single is false
                        550, 450, 500, 800},
                mPaint);

        /**
         * draw rectangle
         *
         * Recf is more exacter than Rect
         *
         */
        canvas.drawRect(
                500, 200, 1000, 400,
                mPaint);

        Rect rect = new Rect(500, 100, 1000, 150);
        canvas.drawRect(rect, mPaint);

        RectF rectF = new RectF(500, 50, 1000, 75);
        canvas.drawRect(rectF, mPaint);

        /**
         * drawable roundRect,rx and ry is ellipse(椭圆) two radius
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(500, 500, 1000, 700, 20, 20, mPaint);
        }

        RectF rectF1 = new RectF(500, 750, 1000, 950);
        canvas.drawRoundRect(rectF1, 30, 30, mPaint);

        // radius is half of width,radius is half of high
        RectF rectF2 = new RectF(500, 1000, 1000, 1200);
        canvas.drawRoundRect(rectF2, 250, 100, mPaint);

        // rectangle is square(正方形).
        RectF rectF3 = new RectF(500, 1300, 1000, 1800);
        mPaint.setColor(mGreen);
        canvas.drawRect(rectF3, mPaint);
        mPaint.setColor(mBlack);
        canvas.drawRoundRect(rectF3, 400, 400, mPaint);

        /**
         * draw ellipse,四个点是外接矩形
         */
        RectF rect1 = new RectF(100, 100, 800, 400);
        canvas.drawOval(rect1, mPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawOval(200, 500, 200, 700, mPaint);
        }

        /**
         * draw circle
         */
        canvas.drawCircle(500, 500, 100, mPaint);
//        -------------------false------------------
        RectF rectF4 = new RectF(100, 100, 800, 400);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF4, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF4, 0, 90, false, mPaint);
//        -------------------true------------------
        RectF rectF5 = new RectF(100, 600, 800, 900);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF5, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF5, 0, 90, true, mPaint);
    }
}
