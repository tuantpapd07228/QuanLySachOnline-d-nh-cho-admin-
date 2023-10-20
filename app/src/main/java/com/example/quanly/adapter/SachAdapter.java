package com.example.quanly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.quanly.R;
import com.example.quanly.model.Sach;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sach> arr;
    public interface XuLiKhiNhanVaoSach{Sach xulikhinhanvaosach(Sach sach);}

    XuLiKhiNhanVaoSach xuLiKhiNhanVaoSach;

    public SachAdapter(Context context, ArrayList<Sach> arr, XuLiKhiNhanVaoSach xuLiKhiNhanVaoSach) {
        this.context = context;
        this.arr = arr;
        this.xuLiKhiNhanVaoSach = xuLiKhiNhanVaoSach;
    }

    @Override
    public int getCount() {
        if (arr != null){
            return arr.size();
        }
        return 0;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView tensach, tacgia, mota, giatien;
        ImageView img;
        CardView cardView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.grid_sach, viewGroup, false);
            view = LayoutInflater.from(context).inflate(R.layout.grid_sach, viewGroup, false);
            holder.img = view.findViewById(R.id.imgsachtheotheloai);
            holder.tensach = view.findViewById(R.id.tensachtheotheloai);
            holder.tacgia = view.findViewById(R.id.tacgiatheotheloai);
            holder.mota = view.findViewById(R.id.motasachtheotungtheloai);
            holder.giatien = view.findViewById(R.id.giabansachthetungtheloai);
            holder.cardView = view.findViewById(R.id.cardviewtheotheloai);
            view.setTag(holder);
        }else{
            view.getTag();

        }
        Sach sach = arr.get(i);

        try {
            int id = context.getResources().getIdentifier("drawable/"+sach.getHinh(), null, context.getPackageName());
            holder.img.setImageResource(id);
            holder.tensach.setText(sach.getTieuDe());
            holder.tacgia.setText(sach.getTacGia());
            holder.giatien.setText(sach.getGiaBan()+"");
            holder.mota.setText(sach.getMota());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    xuLiKhiNhanVaoSach.xulikhinhanvaosach(sach);
                }
            });
        }catch (Exception e){
            System.out.println("loi sachthetheloai 75"+e.getMessage());
        }

        return view;
    }
}
