package com.example.quanly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public class XacNhanDonHanAdapter extends RecyclerView.Adapter<XacNhanDonHanAdapter.ViewHolder> {
    ArrayList<HoaDonOuter> arrouter;
    ArrayList<HoaDonInner> arrinner;
    Context context;
    DoanhThuInnerAdapter adapter;
    ReadData readData;
    LinearLayoutManager linearLayoutManager;
    UpdateData updateData;

    public XacNhanDonHanAdapter(ArrayList<HoaDonOuter> arrouter, Context context) {
        this.arrouter = arrouter;
        this.context = context;
        readData = new ReadData(context);
        updateData = new UpdateData(context);
    }

    @NonNull
    @Override
    public XacNhanDonHanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_xacnhandonhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XacNhanDonHanAdapter.ViewHolder holder, int position) {
        HoaDonOuter outer = arrouter.get(position);
        if (outer == null) return;
        holder.username.setText(outer.getUsernguoimua());
        holder.idhoadon.setText(outer.getIdHoaDon());
        holder.tongtien.setText(outer.getTongTien());
        holder.ngaymua.setText(outer.getNgayMua());
        holder.ten.setText(outer.getTen());
        holder.diachi.setText(outer.getDiaChi());
        holder.sdt.setText(outer.getSdt());
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        readData.getHoaDoninner(outer.getIdHoaDon(), new ReadData.XuLiHoaDonInner() {
            @Override
            public void xulihoadoninner(ArrayList<HoaDonInner> arrhoadon1) {
                arrinner = new ArrayList<>();
                arrinner.addAll(arrhoadon1);
                adapter = new DoanhThuInnerAdapter(arrinner, context);
                holder.recyclerView.setAdapter(adapter);
            }
        });
        holder.xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData.updateDonHangDoiXacNhan(outer.getIdHoaDon(), "vanchuyen");
                holder.xacnhan.setVisibility(View.INVISIBLE);
                holder.btnhuy.setVisibility(View.INVISIBLE);
                holder.tvxacnhan.setVisibility(View.VISIBLE);
            }
        });
        holder.btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData.updateDonHangDoiXacNhan(outer.getIdHoaDon(), "huy");
                holder.xacnhan.setVisibility(View.INVISIBLE);
                holder.btnhuy.setVisibility(View.INVISIBLE);
                holder.tvhuy.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrouter != null) return arrouter.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, idhoadon, tongtien, ngaymua, tvhuy, tvxacnhan, ten, diachi, sdt;
        Button xacnhan, btnhuy;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.xacnhan_username);
            idhoadon = itemView.findViewById(R.id.xacnhan_idhoadon);
            tongtien = itemView.findViewById(R.id.xacnhan_tongtien);
            ten = itemView.findViewById(R.id.xacnhantennguoimua);
            diachi = itemView.findViewById(R.id.xacnhandiachi);
            sdt = itemView.findViewById(R.id.xacnhansdtnguoimua);
            ngaymua = itemView.findViewById(R.id.xacnhan_ngaymua);
            btnhuy = itemView.findViewById(R.id.xacnhanhuy);
            xacnhan = itemView.findViewById(R.id.xacnhan_thanhcong);
            recyclerView = itemView.findViewById(R.id.xacnhan_recycleviewouter);
            tvhuy = itemView.findViewById(R.id.xacnhan_texthuy);
            tvxacnhan = itemView.findViewById(R.id.xacnhan_text);
        }
    }
}
