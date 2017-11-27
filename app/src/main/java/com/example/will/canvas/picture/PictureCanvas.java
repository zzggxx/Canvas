package com.example.will.canvas.picture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by will on 2017/11/24.
 */

public class PictureCanvas extends View {

    private Paint mPaint;
    private int mBlack = 0xffff0000;
    private int mGreen = 0xff00ff00;
    private int mWidth, mHigh;
    private Picture mPicture = new Picture();

    public PictureCanvas(Context context) {
        this(context, null);
    }

    public PictureCanvas(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
//        super(context, attrs);
//        init();

        recording();
    }

    public PictureCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
//
        recording();
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
        mPicture.draw(canvas);
    }
//        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));

    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);

        mPicture.endRecording();
    }
}
