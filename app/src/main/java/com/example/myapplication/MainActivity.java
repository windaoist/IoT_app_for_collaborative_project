package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.ui.devicePageFragment;
import com.example.myapplication.ui.humidityMonitorFragment;
import com.example.myapplication.ui.mainPageFragment;
import com.example.myapplication.ui.tempMonitorFragment;

public class MainActivity extends AppCompatActivity {
    private mainPageFragment mainPage;
    private tempMonitorFragment tempMonitor;
    private devicePageFragment devicePage;
    private humidityMonitorFragment humidityMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mainPage = new mainPageFragment();
        tempMonitor = new tempMonitorFragment();
        humidityMonitor=new humidityMonitorFragment();
        devicePage = new devicePageFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_main, mainPage);
        transaction.commit();
    }

    public void switchToMainPage() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, mainPage).commit();
        //恢复显示导航栏
        findViewById(R.id.bottomBarFragment).setVisibility(View.VISIBLE);
    }

    public void switchToTempMonitor() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, tempMonitor).commit();
        //隐藏导航栏
        findViewById(R.id.bottomBarFragment).setVisibility(View.GONE);
    }

    public void switchToHumidityMonitor() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, humidityMonitor).commit();
        //隐藏导航栏
        findViewById(R.id.bottomBarFragment).setVisibility(View.GONE);
    }
    public void switchToDevicePage() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, devicePage).commit();
        findViewById(R.id.bottomBarFragment).setVisibility(View.VISIBLE);
    }
}