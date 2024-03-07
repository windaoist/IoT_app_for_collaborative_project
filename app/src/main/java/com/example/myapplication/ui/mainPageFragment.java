package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.Objects;


public class mainPageFragment extends Fragment {


    public mainPageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.poly1).setOnClickListener(v -> {
            ((MainActivity)requireActivity()).switchToTempMonitor();
        });
        view.findViewById(R.id.poly2).setOnClickListener(v -> {
            ((MainActivity)requireActivity()).switchToHumidityMonitor();
        });
        view.findViewById(R.id.poly3).setOnClickListener(v -> {
            ((MainActivity)requireActivity()).switchToAirQualityMonitor();
        });
    }
}