package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.ui.airQualityMonitorFragment;
import com.example.myapplication.ui.dataPageFragment;
import com.example.myapplication.ui.devicePageFragment;
import com.example.myapplication.ui.humidityMonitorFragment;
import com.example.myapplication.ui.mainPageFragment;
import com.example.myapplication.ui.tempMonitorFragment;

public class MainActivity extends AppCompatActivity {
    private mainPageFragment mainPage;
    private tempMonitorFragment tempMonitor;
    private devicePageFragment devicePage;
    private dataPageFragment dataPage;
    private humidityMonitorFragment humidityMonitor;
    private airQualityMonitorFragment airQualityMonitor;
    int currentPage = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mainPage = new mainPageFragment();
        tempMonitor = new tempMonitorFragment();
        humidityMonitor = new humidityMonitorFragment();
        devicePage = new devicePageFragment();
        airQualityMonitor = new airQualityMonitorFragment();
        dataPage = new dataPageFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_main, mainPage);
        transaction.commit();
    }

    // 将dp值转换为像素（px）值
    public static int dpToPx(Context context, float dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    private com.google.android.material.textview.MaterialTextView getCurrentPage(int id) {
        switch (id) {
            case 0:
                return findViewById(R.id.homepageText);
            case 1:
                return findViewById(R.id.deviceText);
            case 2:
                return findViewById(R.id.dataText);
            default:
                return null;
        }
    }

    //
    private VectorDrawable getCurrentDrawable(int id, boolean isWhite) {
        //注：0为黑色，1为白色
        switch (id) {
            case 0:
                if (!isWhite) {
                    return (VectorDrawable) AppCompatResources.getDrawable(getBaseContext(), R.drawable.homepage);
                } else {
                    return (VectorDrawable) AppCompatResources.getDrawable(getBaseContext(), R.drawable.homepage_white);
                }
            case 1:
                if (!isWhite) {
                    return (VectorDrawable) AppCompatResources.getDrawable(getBaseContext(), R.drawable.device);
                } else {
                    return (VectorDrawable) AppCompatResources.getDrawable(getBaseContext(), R.drawable.device_white);
                }
            case 2:
                if (!isWhite) {
                    return (VectorDrawable) AppCompatResources.getDrawable(getBaseContext(), R.drawable.data);
                } else {
                    return (VectorDrawable) AppCompatResources.getDrawable(getBaseContext(), R.drawable.data_white);
                }
            default:
                return null;
        }
    }

    private void setMarkCirclePos(int id) {
        com.google.android.material.card.MaterialCardView mark = findViewById(R.id.markCircle);
        LayoutParams layoutParams = new LayoutParams(mark.getHeight(), mark.getWidth());
        // 设置约束条件
        layoutParams.startToStart = id;
        layoutParams.topToTop = id;
        layoutParams.endToEnd = id;
        layoutParams.bottomToBottom = id;
        layoutParams.topMargin = dpToPx(getBaseContext(), -12);
        mark.setLayoutParams(layoutParams);
    }

    public void changeDrawableTint(int id, int target) {
        if (currentPage == -1) {
            return;
        }
        com.google.android.material.textview.MaterialTextView targetText = findViewById(id);
        //将字改为白色
        targetText.setTextColor(Color.WHITE);
        //更改图像为白色
        VectorDrawable drawable_white = getCurrentDrawable(target, true);
        if (drawable_white != null) {
            drawable_white.setBounds(0, 0, drawable_white.getMinimumWidth(), drawable_white.getMinimumHeight());
            targetText.setCompoundDrawables(null, drawable_white, null, null);
        }
        //将原有图像变回黑色
        com.google.android.material.textview.MaterialTextView currentPageText = getCurrentPage(currentPage);
        if (currentPageText != null) {
            VectorDrawable drawableBlack = getCurrentDrawable(currentPage, false);
            //设置
            if (drawableBlack != null) {
                drawableBlack.setBounds(0, 0, drawableBlack.getMinimumWidth(), drawableBlack.getMinimumHeight());
                currentPageText.setCompoundDrawables(null, drawableBlack, null, null);
                currentPageText.setTextColor(Color.BLACK);
            }
        }
        //更改指示圆形位置
        setMarkCirclePos(id);
    }

    public void switchToMainPage() {
        //恢复显示导航栏
        findViewById(R.id.bottomBarFragment).setVisibility(View.VISIBLE);
        changeDrawableTint(R.id.homepageText, 0);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, mainPage).commit();
        currentPage = 0;
    }
    public void switchToDevicePage() {
        findViewById(R.id.bottomBarFragment).setVisibility(View.VISIBLE);
        changeDrawableTint(R.id.deviceText, 1);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, devicePage).commit();
        currentPage = 1;
    }

    public void switchToDataPage() {
        findViewById(R.id.bottomBarFragment).setVisibility(View.VISIBLE);
        changeDrawableTint(R.id.dataText, 2);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, dataPage).commit();
        currentPage = 2;
    }
    public void switchToTempMonitor() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, tempMonitor).commit();
        //隐藏导航栏
        findViewById(R.id.bottomBarFragment).setVisibility(View.GONE);
        currentPage = -1;
    }

    public void switchToHumidityMonitor() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, humidityMonitor).commit();
        findViewById(R.id.bottomBarFragment).setVisibility(View.GONE);
        currentPage = -1;
    }

    public void switchToAirQualityMonitor() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, airQualityMonitor).commit();
        findViewById(R.id.bottomBarFragment).setVisibility(View.GONE);
        currentPage = -1;
    }




}