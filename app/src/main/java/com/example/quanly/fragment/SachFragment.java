package com.example.quanly.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.quanly.R;
import com.example.quanly.adapter.SachAdapter;
import com.example.quanly.data.ReadData;
import com.example.quanly.model.Sach;

import java.util.ArrayList;


public class SachFragment extends Fragment {

    GridView gridView;
    SachAdapter adapter;
    ArrayList<Sach> arrsach;
    ReadData readData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        gridView = view.findViewById(R.id.sachgridview);
        readData = new ReadData(getContext());
        arrsach = new ArrayList<>();
        adapter = new SachAdapter(getContext(), arrsach, new SachAdapter.XuLiKhiNhanVaoSach() {
            @Override
            public Sach xulikhinhanvaosach(Sach sach) {
                return null;
            }
        });
        gridView.setAdapter(adapter);
        setData();







        return view;
    }

    public void setData(){
        readData.getSach(new ReadData.XuLiSauKhiLayDataSach() {
            @Override
            public void xulisaukhilaydatasach(ArrayList<Sach> arr) {
                arrsach.addAll(arr);
                adapter.notifyDataSetChanged();
            }
        });
    }


}