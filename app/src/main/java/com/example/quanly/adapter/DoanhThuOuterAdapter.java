package com.example.quanly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanly.R;
import com.example.quanly.data.ReadData;
import com.example.quanly.model.HoaDonInner;
import com.example.quanly.model.HoaDonOuter;

import java.util.ArrayList;

public class DoanhThuOuterAdapter extends RecyclerView.Adapter<DoanhThuOuterAdapter.ViewHolder> {
    ArrayList<HoaDonOuter> arrouter;
    Context context;
    ArrayList<HoaDonInner> arrinner;
    DoanhThuInnerAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ReadData readData;

    public DoanhThuOuterAdapter(ArrayList<HoaDonOuter> arrouter, Context context) {
        this.arrouter = arrouter;
        this.context = context;

        readData = new ReadData(context);
    }

    @NonNull
    @Override
    public DoanhThuOuterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_doanhthu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoanhThuOuterAdapter.ViewHolder holder, int position) {
        HoaDonOuter hoaDonOuter = arrouter.get(position);
        if (hoaDonOuter == null )return;
        holder.user.setText(hoaDonOuter.getUsernguoimua());
        holder.idhoadon.setText(hoaDonOuter.getIdHoaDon());
        holder.tongtien.setText(hoaDonOuter.getTongTien());
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.ngaymua.setText(hoaDonOuter.getNgayMua());

        readData.getHoaDoninner(hoaDonOuter.getIdHoaDon(), new ReadData.XuLiHoaDonInner() {
            @Override
            public void xulihoadoninner(ArrayList<HoaDonInner> arrhoadon1) {
                arrinner = new ArrayList<>();
                arrinner.addAll(arrhoadon1);
                adapter = new DoanhThuInnerAdapter(arrinner, context);
                holder.recyclerView.setAdapter(adapter);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrouter != null) return arrouter.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user, idhoadon, tongtien, ngaymua;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.doanhthu_username);
            idhoadon = itemView.findViewById(R.id.doanhthu_idhoadon);
            tongtien = itemView.findViewById(R.id.doanhthu_tongtien);
            recyclerView = itemView.findViewById(R.id.doanhthurecycleviewouter);
            ngaymua = itemView.findViewById(R.id.doanhthu_ngaymua);
        }
    }



}
