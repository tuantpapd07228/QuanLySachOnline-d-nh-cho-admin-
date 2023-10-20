package com.example.quanly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanly.R;
import com.example.quanly.data.ReadData;
import com.example.quanly.data.UpdateData;
import com.example.quanly.model.HoaDonInner;
import com.example.quanly.model.HoaDonOuter;

import java.util.ArrayList;

public class VanChuyenAdapter extends RecyclerView.Adapter<VanChuyenAdapter.ViewHolder> {
    ArrayList<HoaDonOuter> arrouter;
    ArrayList<HoaDonInner> arrinner;
    Context context;
    DoanhThuInnerAdapter adapter;
    ReadData readData;
    LinearLayoutManager linearLayoutManager;
    UpdateData updateData;

    public VanChuyenAdapter(ArrayList<HoaDonOuter> arrouter, Context context) {
        this.arrouter = arrouter;
        this.context = context;
        readData = new ReadData(context);
        updateData = new UpdateData(context);
    }

    @NonNull
    @Override
    public VanChuyenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_vanchuyen_donhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VanChuyenAdapter.ViewHolder holder, int position) {
        HoaDonOuter outer =arrouter.get(position);
        if (outer == null) return;
        linearLayoutManager = new LinearLayoutManager(context);
        holder.ten.setText(outer.getTen());
        holder.diachi.setText(outer.getDiaChi());
        holder.sdt.setText(outer.getSdt());
        holder.idhoadon.setText(outer.getIdHoaDon());
        holder.username.setText(outer.getUsernguoimua());
        holder.tongtien.setText(outer.getTongTien());
        holder.ngaymua.setText(outer.getNgayMua());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.giaothanhcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData.updateDonHangVanChuyenThanhCong(outer.getIdHoaDon(), "hoanthanh");
                holder.giaothanhcong.setVisibility(View.INVISIBLE);
                holder.giaothatbai.setVisibility(View.INVISIBLE);
                holder.thanhcong.setVisibility(View.VISIBLE);
            }
        });
        holder.giaothatbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData.updateDonHangVanChuyenThanhCong(outer.getIdHoaDon(), "huy");
                holder.giaothanhcong.setVisibility(View.INVISIBLE);
                holder.giaothatbai.setVisibility(View.INVISIBLE);
                holder.thatbai.setVisibility(View.VISIBLE);
            }
        });
        readData.getHoaDoninner(outer.getIdHoaDon(), new ReadData.XuLiHoaDonInner() {
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
        TextView username, idhoadon, tongtien, ngaymua, thanhcong, thatbai, ten, sdt, diachi;
        RecyclerView recyclerView;
        Button giaothanhcong, giaothatbai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.vanchuyen_username);
            idhoadon = itemView.findViewById(R.id.vanchuyen_idhoadon);
            tongtien = itemView.findViewById(R.id.vanchuyen_tongtien);
            ten = itemView.findViewById(R.id.vanchuyentennguoimua);
            sdt = itemView.findViewById(R.id.vanchuyensdtnguoimua);
            diachi = itemView.findViewById(R.id.vanchuyendiachi);
            ngaymua = itemView.findViewById(R.id.vanchuyen_ngaymua);
            recyclerView = itemView.findViewById(R.id.vanchuyen_recycleviewouter);
            giaothanhcong = itemView.findViewById(R.id.vanchuyenthanhcong);
            giaothatbai = itemView.findViewById(R.id.vanchuyenkhongthanhcong);
            thanhcong = itemView.findViewById(R.id.vanchuyentext);
            thatbai = itemView.findViewById(R.id.vanchuyentextthatbai);
        }
    }
}
