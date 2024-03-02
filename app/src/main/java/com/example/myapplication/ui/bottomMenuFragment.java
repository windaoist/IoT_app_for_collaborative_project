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

public class bottomMenuFragment extends Fragment {

    public bottomMenuFragment() {
    }

    public static bottomMenuFragment newInstance(String param1, String param2) {
        bottomMenuFragment fragment = new bottomMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //转换事件监听器
        view.findViewById(R.id.homepageText).setOnClickListener(v -> ((MainActivity) requireActivity()).switchToMainPage());
        view.findViewById(R.id.deviceText).setOnClickListener(v -> ((MainActivity) requireActivity()).switchToDevicePage());
    }
}