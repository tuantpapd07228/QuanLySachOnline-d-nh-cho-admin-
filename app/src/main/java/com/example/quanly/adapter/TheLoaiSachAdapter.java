package com.example.quanly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanly.R;
import com.example.quanly.model.TheLoaiSoLuong;

import java.util.ArrayList;

public class TheLoaiSachAdapter extends RecyclerView.Adapter<TheLoaiSachAdapter.TheLoaiSachViewHolder>  {
    ArrayList<TheLoaiSoLuong> arr;
    Context context;
    public interface XuLiKhiChonSachTheoTheLoai{void xulisachthetheloai(String tentheloai);}
    XuLiKhiChonSachTheoTheLoai xuLiKhiChonSachTheoTheLoai;

    public TheLoaiSachAdapter(ArrayList<TheLoaiSoLuong> arr, Context context, XuLiKhiChonSachTheoTheLoai xuLiKhiChonSachTheoTheLoai) {
        this.arr = arr;
        this.context = context;
        this.xuLiKhiChonSachTheoTheLoai = xuLiKhiChonSachTheoTheLoai;
    }

    @NonNull
    @Override
    public TheLoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_theloaisach, parent, false);
        return new TheLoaiSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiSachViewHolder holder, int position) {
        TheLoaiSoLuong theLoaiSach = arr.get(position);
        if (theLoaiSach == null){
            return;
        }
        holder.ten.setText(theLoaiSach.getTen());
        holder.soluong.setText(theLoaiSach.getSoluong()+"");
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLiKhiChonSachTheoTheLoai.xulisachthetheloai(theLoaiSach.getTen());
                Toast.makeText(context, "the loai sach "+ theLoaiSach.getTen(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arr != null){
            return arr.size();
        }
        return 0;
    }

    public class TheLoaiSachViewHolder extends RecyclerView.ViewHolder {
        private TextView ten, soluong;
        private ImageView imv;
        private CardView cardview;
        public TheLoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardviewtheloai);
            ten= itemView.findViewById(R.id.theloaisach);
            soluong = itemView.findViewById(R.id.soluongsach);
            imv = itemView.findViewById(R.id.imvtls);
        }
    }
}

