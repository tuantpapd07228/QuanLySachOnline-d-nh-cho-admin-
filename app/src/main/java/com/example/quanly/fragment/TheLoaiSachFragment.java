package com.example.quanly.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanly.R;
import com.example.quanly.adapter.TheLoaiSachAdapter;
import com.example.quanly.data.ReadData;
import com.example.quanly.model.TheLoaiSoLuong;

import java.util.ArrayList;


public class TheLoaiSachFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReadData readData;

    public ArrayList<TheLoaiSoLuong> arr;
    public static TheLoaiSachAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_the_loai_sach, container, false);
        readData = new ReadData(getContext());
        recyclerView = view.findViewById(R.id.rclview);

        arr = new ArrayList<>();
        adapter = new TheLoaiSachAdapter(arr, getContext(), new TheLoaiSachAdapter.XuLiKhiChonSachTheoTheLoai() {
            @Override
            public void xulisachthetheloai(String idtheloai) {
            }
        });
        getdata();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void getdata(){
        readData.getTheLoaiSach(new ReadData.XuLiTheLoai() {
            @Override
            public void xulitheloai(ArrayList<TheLoaiSoLuong> arr1) {
                arr.addAll(arr1);
                adapter.notifyDataSetChanged();
            }
        });
    }
}