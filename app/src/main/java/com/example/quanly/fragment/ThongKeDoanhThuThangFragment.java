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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class ThongKeDoanhThuThangFragment extends Fragment {
    ReadData readData;
    ArrayList<HoaDonOuter> arrouter;
    TextView doanhthu, tonghoadon, tongsach, startday, endday;
    RecyclerView recyclerView;
    ThongKeHoaDonTheoNgayThangAdapter adapter;
    int year1 , month1, day1, day2, month2, year2;
    SimpleDateFormat simpleDateFormat;
    Calendar startcalendar, endcalendar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke_doanh_thu_thang, container, false);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        startday = view.findViewById(R.id.dauthang);
        endday = view.findViewById(R.id.cuoithang);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        startcalendar = Calendar.getInstance();
        endcalendar = Calendar.getInstance();
        recyclerView = view.findViewById(R.id.khoanthoigianrecycleview);
        recyclerView.setLayoutManager(linearLayoutManager);
        doanhthu = view.findViewById(R.id.thang_tongdoanhthu);
        tonghoadon = view.findViewById(R.id.thang_tonghoadon);
        tongsach = view.findViewById(R.id.thang_tongsachdaban);
        readData = new ReadData(getContext());

//        recyclerView.setAdapter(adapter);

//        readData.thongKeDoanhThu();
        Calendar calendar = Calendar.getInstance();
        day1 = calendar.get(Calendar.DAY_OF_MONTH);
        month1 = calendar.get(Calendar.MONTH);
        year1 = calendar.get(Calendar.YEAR);
        day2 = calendar.get(Calendar.DAY_OF_MONTH);
        month2 = calendar.get(Calendar.MONTH);
        year2 = calendar.get(Calendar.YEAR);
        startday.setText(year1+"/"+(month1+1)+"/"+1);
        endday.setText(year2+"/"+(month2+1)+"/"+day2);
        startday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker1(startday);
            }
        });
        endday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker2(endday);

            }
        });
        getData(startday.getText().toString(), endday.getText().toString());

        return view;
    }

    private void showDatePicker1(TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String selectday = i +"/" +(i1 + 1) +"/" + i2;
                textView.setText(selectday);
                day1 = i2;
                month1 = i1;
                year1 = i;
                getData(startday.getText().toString(), endday.getText().toString());

            }
        },year1,month1,day1);


        datePickerDialog.show();

    }

    private void showDatePicker2(TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String selectday = i +"/" +(i1 + 1) +"/" + i2;
                textView.setText(selectday);
                day2 = i2;
                month2 = i1;
                year2 = i;
                getData(startday.getText().toString(), endday.getText().toString());
            }
        },year2,month2,day2);
        datePickerDialog.show();

        int result = startcalendar.compareTo(endcalendar);
        if (result >= 0){
                // end lon hon
        } else {
            day1 = day2 -1;
            month1 = month2;
            year1 = year2;
            if (day1 == 0) {
                day1 = 30;
                month1 = month1 -1;
                if (month1 == 0){
                    month1 = 12;
                    year1 = year1 -1;

                }
            }
            startday.setText(year1 + "/" +(month1 + 1) + "/" + day1);
            getData(startday.getText().toString(), endday.getText().toString());
        }
    }

    private void getData(String startday, String endday){
        readData.thongKeDoanhThu("", startday, endday, "hoanthanh", new ReadData.XuLiDoanhThu() {
            @Override
            public void xulidoanhthu(ArrayList<HoaDonOuter> arhoadondoanhthu) {
                arrouter = new ArrayList<>();
                arrouter.addAll(arhoadondoanhthu);
                adapter = new ThongKeHoaDonTheoNgayThangAdapter(arrouter, getContext());
                recyclerView.setAdapter(adapter);
                setData();
            }
        });
    }

    public void setData(){

        int tongtien1 =0;
        int tonghoadon1 =0;
        int tongtienship = 0;
        for (HoaDonOuter h : arrouter) {
            tongtien1 += Integer.parseInt(h.getTongTien());
            tongtienship += 30000;
            tonghoadon1 ++;
        }
        doanhthu.setText(tongtien1+tongtienship+"");
        tonghoadon.setText(tonghoadon1+"");
        readData.getTongSachDaBan("", startday.getText().toString(), endday.getText().toString(), new ReadData.XuLiTongSachDaBan() {
            @Override
            public void xuliSachDaBan(int sotongsach1) {
                tongsach.setText(sotongsach1+"");
            }
        });
    }
}