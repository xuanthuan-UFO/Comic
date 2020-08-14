package com.xuanthuan.comic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xuanthuan.comic.R;

public class Fragment_Summary extends Fragment {
    public TextView txtsummary;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container,false);
        txtsummary = view.findViewById(R.id.txtsummary);
        return view;
    }
}
