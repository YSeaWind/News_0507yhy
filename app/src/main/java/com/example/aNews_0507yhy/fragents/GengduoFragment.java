package com.example.aNews_0507yhy.fragents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aNews_0507yhy.R;


public class GengduoFragment extends Fragment {
    private View view;
    private TextView gengduo;
    public GengduoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_gengduo, container, false);
       initView();
        return view;
    }

    private void initView() {
        gengduo = view.findViewById(R.id.tv_gd);
    }
}
