package com.example.quanly.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Fragment extends FragmentStateAdapter {
    public ViewPager2Fragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new ThongKeHoaDonTheoNgayFragment();
        if (position == 1) return new ThongKeDoanhThuTheoKhoangThoiGianFragment();
        if (position == 2) return new ThongKeDoanhThuThangFragment();
        if (position == 3) return new ThongKeDonHangDangVanChuyenFragment();
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
