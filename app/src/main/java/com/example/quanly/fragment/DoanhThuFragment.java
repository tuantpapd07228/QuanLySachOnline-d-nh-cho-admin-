package com.example.quanly.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanly.R;
import com.example.quanly.adapter.DoanhThuOuterAdapter;
import com.example.quanly.data.ReadData;
import com.example.quanly.model.HoaDonOuter;

import java.util.ArrayList;

public class DoanhThuFragment extends Fragment {
    ArrayList<HoaDonOuter> arrouter;
    DoanhThuOuterAdapter adapter;
    ReadData readData;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        recyclerView = view.findViewById(R.id.doanhthurecyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrouter = new ArrayList<>();
        readData = new ReadData(getContext());
        adapter = new DoanhThuOuterAdapter(arrouter, getContext());
        readData.getHoaDonOuter(new ReadData.XuLiHoaDonOuter() {
            @Override
            public void xulihoadonouter(ArrayList<HoaDonOuter> arrayList) {
                arrouter.addAll(arrayList);
                adapter.notifyDataSetChanged();
            }
        });


        recyclerView.setAdapter(adapter);

        return view;
    }
}