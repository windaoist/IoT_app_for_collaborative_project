package com.example.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class SectorView extends View {
    float radius = 0;
    float startAngle = 0;
    float sweepAngle = 0;
    RectF rect = new RectF();
    int color=0;
    Paint paint;

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
        canvas.drawArc(rect, startAngle, sweepAngle, true, paint);
    }

    void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SectorView);
        radius = attributes.getDimension(R.styleable.SectorView_sectorRadius, 0);
        startAngle=attributes.getFloat(R.styleable.SectorView_sectorStartAngle,0);
        sweepAngle=attributes.getFloat(R.styleable.SectorView_sectorSweepAngle,0);
        color=attributes.getColor(R.styleable.SectorView_sectorColor,0);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        attributes.recycle();
    }
}
