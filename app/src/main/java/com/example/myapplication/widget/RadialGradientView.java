package com.example.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class RadialGradientView extends View {
    float radius = 0;
    int gradientStart = 0;
    int gradientEnd = 0;
    Paint paint;
    RectF rect = new RectF();
    RadialGradient gradient;

    public RadialGradientView(Context context) {
        this(context, null);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawArc(rect, 0, 360, true, paint);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RadialGradientView);
        radius = attributes.getDimension(R.styleable.RadialGradientView_radialRadius, 0);
        gradientStart = attributes.getColor(R.styleable.RadialGradientView_radialGradientStart, Color.BLACK);
        gradientEnd = attributes.getColor(R.styleable.RadialGradientView_radialGradientEnd, Color.BLACK);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        float h = attributes.getDimension(R.styleable.RadialGradientView_android_layout_height, 0);
        float w = attributes.getDimension(R.styleable.RadialGradientView_android_layout_width, 0);
        int[] colors={gradientStart,gradientEnd,Color.WHITE};
        float[] pos={0.45f,0.90f,1.0f};
        //高斯模糊的实现较为复杂，这里先用整个线性渐变代替
        gradient = new RadialGradient(h / 2, w / 2, radius, colors,pos, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        attributes.recycle();
    }
}
