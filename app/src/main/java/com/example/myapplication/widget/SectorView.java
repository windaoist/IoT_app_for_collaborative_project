package com.example.myapplication.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class SectorView extends View {
    float radius = 0;
    float stroke = 0;
    float startAngle = 0;
    float sweepAngle = 0;
    int gradientStart = 0;
    int gradientEnd = 0;
    RectF rect = new RectF();
    Paint sectorPaint;
    Paint circlePaint;
    SweepGradient gradient;

    public SectorView(Context context) {
        this(context, null);
    }

    public SectorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        rect.top = (float) getHeight() / 2 - radius;
        rect.bottom = (float) getHeight() / 2 + radius;
        rect.left = (float) getWidth() / 2 - radius;
        rect.right = (float) getWidth() / 2 + radius;
        //圆弧本身
        canvas.drawArc(rect, startAngle, sweepAngle, false, sectorPaint);
        circlePaint.setColor(gradientStart);
        float startDegree = (float) (Math.PI / 180f * startAngle);
        float endDegree = (float) (Math.PI / 180f * (sweepAngle + startAngle));
        //起点圆
        canvas.drawCircle((float) (rect.left + rect.width() / 2f + radius * Math.cos(startDegree)), //圆心x
                (float) (rect.top + rect.height() / 2f + radius * Math.sin(startDegree)), //圆心y
                stroke / 2f,  //半径
                circlePaint);
        //末端圆
        circlePaint.setColor(gradientEnd);
        canvas.drawCircle((float) (rect.left + rect.width() / 2f + radius * Math.cos(endDegree)), //圆心x
                (float) (rect.top + rect.height() / 2f + radius * Math.sin(endDegree)), //圆心y
                stroke / 2f,  //半径
                circlePaint);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SectorView);
        radius = attributes.getDimension(R.styleable.SectorView_sectorRadius, 0);
        stroke = attributes.getDimension(R.styleable.SectorView_sectorStroke, 0);
        startAngle = attributes.getFloat(R.styleable.SectorView_sectorStartAngle, 0);
        sweepAngle = attributes.getFloat(R.styleable.SectorView_sectorSweepAngle, 0);
        gradientStart = attributes.getColor(R.styleable.SectorView_sectorGradientStart, Color.BLACK);
        gradientEnd = attributes.getColor(R.styleable.SectorView_sectorGradientEnd, Color.BLACK);
        sectorPaint = new Paint();
        sectorPaint.setStyle(Paint.Style.STROKE);
        sectorPaint.setColor(Color.BLACK);
        sectorPaint.setAntiAlias(true);
        sectorPaint.setDither(true);
        sectorPaint.setStrokeWidth(stroke);
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);
        //由于此处直接调用getHeight()与getWidth()不能得到正确的数值，只能手动调用系统参数
        float h = attributes.getDimension(R.styleable.SectorView_android_layout_height, 0);
        float w = attributes.getDimension(R.styleable.SectorView_android_layout_width, 0);
        int[] colors = {gradientStart, gradientEnd};
        float[] pos = {-0.017f, (startAngle + sweepAngle) / 360f - 0.017f};
        gradient = new SweepGradient(w / 2,
                h / 2,
                colors,
                pos);
        sectorPaint.setShader(gradient);
        attributes.recycle();
    }
}
