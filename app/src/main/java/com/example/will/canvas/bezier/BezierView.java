package com.example.will.canvas.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by will on 2017/11/28.
 */

public class BezierView extends View {

    private Paint mainPaint;
    private int colorBlack = 0xff000000;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mainPaint = new Paint();
        mainPaint.setColor(colorBlack);
        mainPaint.setStrokeWidth(10f);
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        Path path = new Path();

        path.moveTo(700, 0);
        path.quadTo(0, 0, 0, 1000);

        canvas.drawPath(path, mainPaint);

        super.onDraw(canvas);
    }
}
