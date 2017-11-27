package com.example.will.canvas.canvas_go;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by will on 2017/11/24.
 */

public class SloopCanvasView extends View {

    private Paint mPaint;
    private int mBlack = 0xffff0000;
    private int mGreen = 0xff00ff00;
    private int mWidth, mHigh;

    public SloopCanvasView(Context context) {
        this(context, null);
    }

    public SloopCanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SloopCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHigh = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 位移
         */
//        canvas.translate(100, 100);
//        canvas.drawCircle(0, 0, 50, mPaint);
//
//        mPaint.setColor(mGreen);
//        canvas.translate(300, 300);
//        canvas.drawCircle(0, 0, 200, mPaint);


//        canvas.translate(mWidth / 2, mHigh / 2);
//
//        RectF rectF = new RectF(0, 0, 400, 400);
//        canvas.drawRect(rectF, mPaint);
//
//        mPaint.setColor(mGreen);
//        canvas.scale(-0.5f,-0.5f,100,0);
//        canvas.drawRect(rectF,mPaint);

        /**
         * 缩放叠加小案例
         */
//        canvas.translate(mWidth / 2, mHigh / 2);
//        RectF rectF = new RectF(-700, -700, 700, 700);
//        mPaint.setStyle(Paint.Style.STROKE);
//        for (int i = 0; i < 70; i++) {
//            canvas.scale(0.95f, 0.95f);
//            canvas.drawRect(rectF, mPaint);
//        }


        /**
         * rotate 小案例
         */
        canvas.translate(mWidth / 2, mHigh / 2);
        canvas.drawCircle(0, 0, 500, mPaint);
        mPaint.setColor(mGreen);
        canvas.drawCircle(0, 0, 400, mPaint);

        for (int i = 0; i < 20; i++) {
            canvas.drawLine(0, 400, 0, 500, mPaint);
            canvas.rotate(20);
        }


    }
}
