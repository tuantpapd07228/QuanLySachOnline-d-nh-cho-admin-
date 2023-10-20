package com.example.quanly.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanly.R;
import com.example.quanly.adapter.VanChuyenAdapter;
import com.example.quanly.data.ReadData;
import com.example.quanly.model.HoaDonOuter;

import java.util.ArrayList;


public class VanChuyenFragment extends Fragment {

    VanChuyenAdapter adapter;
    ArrayList<HoaDonOuter> arrouter;
    RecyclerView recyclerView;
    ReadData readData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_van_chuyen, container, false);
        arrouter = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView = view.findViewById(R.id.vanchuyenrecyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        readData = new ReadData(getContext());
        readData.getHoaDonVanChuyen(new ReadData.XuLiHoaDonVanChuyen() {
            @Override
            public void xulihoadonvanchuyen(ArrayList<HoaDonOuter> arrayList) {
                arrouter.addAll(arrayList);
                adapter = new VanChuyenAdapter(arrouter, getContext());
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }
}