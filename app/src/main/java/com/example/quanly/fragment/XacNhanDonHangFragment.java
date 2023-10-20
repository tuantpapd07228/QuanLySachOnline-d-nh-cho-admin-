package com.example.quanly.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanly.R;
import com.example.quanly.adapter.DoanhThuInnerAdapter;
import com.example.quanly.adapter.XacNhanDonHanAdapter;
import com.example.quanly.data.ReadData;
import com.example.quanly.model.HoaDonInner;
import com.example.quanly.model.HoaDonOuter;

import java.util.ArrayList;


public class XacNhanDonHangFragment extends Fragment {
    ArrayList<HoaDonOuter> arrouter;
    ReadData readData;
    LinearLayoutManager linearLayoutManager;
    XacNhanDonHanAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_xac_nhan_don_hang, container, false);
        recyclerView = view.findViewById(R.id.xacnhandonhangrecycleview);
        readData = new ReadData(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        readData.getHoaDonDoiXacNhan(new ReadData.XuLiHoaDonDoiXacNhan() {
            @Override
            public void xulihoadondoixacnhan(ArrayList<HoaDonOuter> arrayList) {
                arrouter = new ArrayList<>();
                arrouter.addAll(arrayList);
                adapter = new XacNhanDonHanAdapter(arrouter, getContext());
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }
}