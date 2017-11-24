package com.example.will.canvas.Pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by will on 2017/11/24.
 */

public class PieView extends View {

    private Paint mPaint;
    private int mWidth, mHight;
    private ArrayList<PieData> mPieDatas;
    private float startAngle;
    private int[] mColors = {
            0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        1
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(20f);
//        mPaint.setStyle(Paint.Style.STROKE);
    }

    //    2
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHight = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPieDatas == null || mPieDatas.size() == 0) {
            return;
        }

//        坐标放置在中心位置
        canvas.translate(mWidth / 2, mHight / 2);
//        起始角度
        float currentAngle = startAngle;
//        半径
        float radius = (float) (Math.min(mWidth, mHight) / 2 * 0.8);
//        外接矩形
        RectF rectF = new RectF(-radius, -radius, radius, radius);
//        圆环基本上都是如此的套路子
        for (int i = 0; i < mPieDatas.size(); i++) {
            PieData pieData = mPieDatas.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectF, currentAngle, pieData.getAngle(), false, mPaint);
            currentAngle += pieData.getAngle();
        }
    }

    /**
     * 别忘记每一次的重绘制都需要进行invalidate();
     */
    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
        invalidate();
    }

    /**
     * 用户给我们的原始数据,我们需要进行深加工的.
     *
     * @param mPieDatas
     */
    public void setmPieDatas(ArrayList<PieData> mPieDatas) {
        this.mPieDatas = mPieDatas;
        initData(mPieDatas);
        invalidate();
    }

    private void initData(ArrayList<PieData> mPieDatas) {

        if (mPieDatas == null || mPieDatas.size() == 0) {
            return;
        }

        float sumVaule = 0;
        for (int i = 0; i < mPieDatas.size(); i++) {

            PieData pieData = mPieDatas.get(i);

            sumVaule += pieData.getValue();

            int j = i % mColors.length;
            pieData.setColor(mColors[j]);
        }

//        须拿到sumValue才能进行计算每一个所占的角度值等数据
        for (int i = 0; i < mPieDatas.size(); i++) {

            PieData pieData = mPieDatas.get(i);

            float percentage = pieData.getValue() / sumVaule;
            float angle = percentage * 360;

            pieData.setPercentage(percentage);
            pieData.setAngle(angle);
        }
    }

    /**
     * 用户不给颜色就用默认的色值
     *
     * @param mColors
     */
    public void setmColors(int[] mColors) {
        this.mColors = mColors;
    }
}
