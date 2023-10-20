package com.example.quanly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.quanly.databinding.ActivityHomeBinding;
import com.example.quanly.databinding.HeaderNavigationBinding;
import com.example.quanly.fragment.DoanhThuFragment;
import com.example.quanly.fragment.SachFragment;
import com.example.quanly.fragment.TheLoaiSachFragment;
import com.example.quanly.fragment.VanChuyenFragment;
import com.example.quanly.fragment.XacNhanDonHangFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeActivity.this,
                binding.drawlayout,
                binding.toolbar,
                R.string.open,
                R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        binding.toolbar.setTitle("Trang chu");
        View view = binding.navigationView.getHeaderView(0);
        TextView tvname = view.findViewById(R.id.tvuser);
        tvname.setText(intent.getStringExtra("hoten"));
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_vanchuyen:
                        fragment = new VanChuyenFragment();
                        binding.toolbar.setTitle("Đon hàng đang vận chuyển");
                        break;
                    case R.id.menu_xemhoadon:
                        fragment = new DoanhThuFragment();
                        binding.toolbar.setTitle("Doanh thu");
                        break;
                    case R.id.menu_xacnhandonhang:
                        fragment = new XacNhanDonHangFragment();
                        binding.toolbar.setTitle("Xác nhận đơn hàng");
                        break;
                    case R.id.menu_thongke:
                        startActivity(new Intent(HomeActivity.this, ThongKeActivity.class));
                        break;
                    case R.id.menu_danhmucsach:
                        fragment = new SachFragment();
                        binding.toolbar.setTitle("Sách");
                        break;
                    case R.id.menu_themtheloaisach:
                        fragment = new TheLoaiSachFragment();
                        binding.toolbar.setTitle("Sách");
                        break;
                }
                if (fragment == null) return false ;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, fragment);
                fragmentTransaction.commit();
                binding.drawlayout.close();

                return true;
            }
        });
    }




}