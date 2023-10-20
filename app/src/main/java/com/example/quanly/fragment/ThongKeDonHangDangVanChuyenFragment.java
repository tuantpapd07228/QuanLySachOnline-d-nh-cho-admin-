package com.example.quanly.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.quanly.R;
import com.example.quanly.adapter.ThongKeHoaDonTheoNgayThangAdapter;
import com.example.quanly.data.ReadData;
import com.example.quanly.model.HoaDonOuter;

import java.util.ArrayList;
import java.util.Calendar;


public class ThongKeDonHangDangVanChuyenFragment extends Fragment {
    ReadData readData;
    ArrayList<HoaDonOuter> arrouter;
    TextView doanhthu, tonghoadon, tongsach;
    RecyclerView recyclerView;
    ThongKeHoaDonTheoNgayThangAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke_don_hang_dang_van_chuyen, container, false);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView = view.findViewById(R.id.vanchuyen_recycleview);
        recyclerView.setLayoutManager(linearLayoutManager);
        doanhthu = view.findViewById(R.id.vanchuyen_tongdoanhthu);
        tonghoadon = view.findViewById(R.id.vanchuyen_tonghoadon);
        tongsach = view.findViewById(R.id.vanchuyen_tongsachdaban);
        arrouter = new ArrayList<>();
        readData = new ReadData(getContext());
        getData();

        return view;
    }



    private void getData(){
        arrouter.clear();
        adapter = new ThongKeHoaDonTheoNgayThangAdapter(arrouter, getContext());
        recyclerView.setAdapter(adapter);
        readData.getHoaDonVanChuyen(new ReadData.XuLiHoaDonVanChuyen() {
            @Override
            public void xulihoadonvanchuyen(ArrayList<HoaDonOuter> arrayList) {
                arrouter.addAll(arrayList);
                adapter.notifyDataSetChanged();
                setData();
            }
        });
    }

    public void setData(){
        int tongtien1 =0;
        int tonghoadon1 =0;
        int tongtienship = 0;
        for (HoaDonOuter h :arrouter) {
            tongtien1 += Integer.parseInt(h.getTongTien());
            tongtienship +=30000;
            tonghoadon1 ++;
        }
        doanhthu.setText(tongtien1+tongtienship+"");
        tonghoadon.setText(tonghoadon1+"");
        readData.getTongSachDangVanChuyen(new ReadData.XuLiTongSachDaBan() {
            @Override
            public void xuliSachDaBan(int tongsach1) {
                tongsach.setText(tongsach1+"");
            }
        });
    }
}