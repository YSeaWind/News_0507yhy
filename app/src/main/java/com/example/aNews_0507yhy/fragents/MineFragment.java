package com.example.aNews_0507yhy.fragents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aNews_0507yhy.R;


public class MineFragment extends Fragment {
    private View view;
    private TextView mine;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        return view;
    }

    private void initView() {
        mine = view.findViewById(R.id.tv_mine);
    }
}
