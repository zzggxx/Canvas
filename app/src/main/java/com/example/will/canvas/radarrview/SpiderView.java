package com.example.will.canvas.radarrview;

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

public class SpiderView extends View {

    /**
     * 数据个数,正多边形的内角算法.这里取用的是弧度.
     */
    private int count = 6;
    private float angle = (float) (Math.PI * 2 / count);

    private float radius;                 //网格最大半径
    private int centerX;                  //中心X
    private int centerY;                  //中心Y
    private String[] titles = {"第1个数据", "第2个数据", "第3个数据", "第4个数据", "第5个数据", "第6个数据"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20};     //各维度分值,6个方向,应该是只有六个角度值的.此处给了8个
    private float maxValue = 100;         //数据最大值
    private Paint mainPaint;              //雷达区画笔
    private Paint valuePaint;             //数据区画笔
    private Paint textPaint;              //文本画笔
    private int colorBlack = 0xff000000;
    private int color1;
    private int color2;

    public SpiderView(Context context) {
        this(context, null);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    //设置标题
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    //设置数值
    public void setData(double[] data) {
        this.data = data;
    }

    //设置最大数值
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    //设置蜘蛛网颜色
    public void setMainPaintColor(int color) {
        mainPaint.setColor(color);
    }

    //设置标题颜色
    public void setTextPaintColor(int color) {
        textPaint.setColor(color);
    }

    //设置覆盖局域颜色
    public void setValuePaintColor(int color) {
        valuePaint.setColor(color);
    }

    private void initPaint() {
        mainPaint = new Paint();
        mainPaint.setColor(colorBlack);
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setAntiAlias(true);

        valuePaint = new Paint();
        color1 = 0xff00ff00;
        valuePaint.setColor(color1);
        valuePaint.setAntiAlias(true);

        textPaint = new Paint();
        color2 = 0xff0000ff;
        textPaint.setColor(color2);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);     //r是蜘蛛丝之间的间距
        for (int i = 1; i < count; i++) {   //中心点不用绘制
            float curR = r * i;             //当前半径
            path.reset();                   //reset不保留内部数据结构，但会保留FillType.
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    //x轴正向的半径处
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();   //闭合路径
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制直线
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            /**
             * cos(0) = 1;
             * sin(0) = 0;
             */
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {
                //第4象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {
                //第1象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {
                //第3象限
                float dis = textPaint.measureText(titles[i]);       //文本长度,左半边的应该是向左移动的.
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {
                //第2象限
                float dis = textPaint.measureText(titles[i]);       //文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {

        Path path = new Path();
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 10, valuePaint);
        }

        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);

        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }


}
