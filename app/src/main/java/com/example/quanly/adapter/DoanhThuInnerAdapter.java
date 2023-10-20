package com.example.quanly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanly.R;
import com.example.quanly.model.HoaDonInner;

import java.util.ArrayList;

public class DoanhThuInnerAdapter extends RecyclerView.Adapter<DoanhThuInnerAdapter.ViewHolder> {
    ArrayList<HoaDonInner> arr;
    Context context;

    public DoanhThuInnerAdapter(ArrayList<HoaDonInner> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public DoanhThuInnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chitiethoadon_inner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoanhThuInnerAdapter.ViewHolder holder, int position) {
        HoaDonInner hoadon = arr.get(position);
        if (hoadon == null) return;
        int id  = context.getResources().getIdentifier("drawable/"+hoadon.getHinhAnh(), null, context.getPackageName());
        holder.img.setImageResource(id);
        holder.tensach.setText(hoadon.getTieuDe());
        holder.giaban.setText(hoadon.getTienSach());
        holder.soluong.setText(hoadon.getSoLuong());
    }

    @Override
    public int getItemCount() {
        if (arr != null) return arr.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tensach, giaban, soluong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.doanhthu_img);
            tensach = itemView.findViewById(R.id.doanhthu_tensach);
            giaban = itemView.findViewById(R.id.doanhthu_giaban);
            soluong = itemView.findViewById(R.id.doanhthu_soluong);
        }
    }

}
