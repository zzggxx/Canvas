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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(100, 100);
        canvas.drawCircle(0, 0, 50, mPaint);

        mPaint.setColor(mGreen);
        canvas.translate(300, 300);
        canvas.drawCircle(0, 0, 200, mPaint);

    }
}
