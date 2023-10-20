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


public class ThongKeHoaDonTheoNgayFragment extends Fragment {
    ReadData readData;
    ArrayList<HoaDonOuter> arrouter;
    TextView doanhthu, tonghoadon, tongsach, ngay;
    RecyclerView recyclerView;
    ThongKeHoaDonTheoNgayThangAdapter adapter;
    int year , month, day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke_hoa_don_theo_ngay, container, false);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        ngay = view.findViewById(R.id.ngay);
        recyclerView = view.findViewById(R.id.ngay_recycleview);
        recyclerView.setLayoutManager(linearLayoutManager);
        doanhthu = view.findViewById(R.id.ngay_tongdoanhthu);
        tonghoadon = view.findViewById(R.id.ngay_tonghoadon);
        tongsach = view.findViewById(R.id.ngay_tongsachdaban);
        arrouter = new ArrayList<>();
        readData = new ReadData(getContext());
//        recyclerView.setAdapter(adapter);

//        readData.thongKeDoanhThu();
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        ngay.setText(year+"/"+(month+1)+"/"+day);
        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        getData(ngay.getText().toString());
//        setData();

        return view;
    }

    private void showDatePicker() {


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String selectday = i +"/" +(i1 + 1) +"/" + i2;
                ngay.setText(selectday);
                day = i2;
                month = i1;
                year = i;
                getData(selectday);
                readData.getTongSachDaBan(year + "/" + (month + 1) + "/" + day, "", "", new ReadData.XuLiTongSachDaBan() {
                    @Override
                    public void xuliSachDaBan(int sotongsach) {
                        tongsach.setText(sotongsach+"");
                    }
                });
            }
        },year,month,day);
        datePickerDialog.show();
    }


    private void getData(String selectday){
        arrouter.clear();
        adapter = new ThongKeHoaDonTheoNgayThangAdapter(arrouter, getContext());
        recyclerView.setAdapter(adapter);

        readData.thongKeDoanhThu(selectday, "", "", "hoanthanh", new ReadData.XuLiDoanhThu() {
            @Override
            public void xulidoanhthu(ArrayList<HoaDonOuter> arhoadondoanhthu) {
                arrouter.addAll(arhoadondoanhthu);
                setData();
                adapter.notifyDataSetChanged();
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
        readData.getTongSachDaBan(year + "/" + (month + 1) + "/" + day, "", "" , new ReadData.XuLiTongSachDaBan() {
            @Override
            public void xuliSachDaBan(int sotongsach) {
                tongsach.setText(sotongsach+"");
            }
        });
    }
}