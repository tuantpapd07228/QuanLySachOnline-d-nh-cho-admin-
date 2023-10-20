package com.example.quanly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quanly.databinding.ActivityThongKeBinding;
import com.example.quanly.fragment.ViewPager2Fragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThongKeActivity extends AppCompatActivity {
    ActivityThongKeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThongKeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewPager2Fragment adapter = new ViewPager2Fragment(this);
        binding.viewpage2.setAdapter(adapter);
        new TabLayoutMediator(binding.tabllayout, binding.viewpage2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Ngày");
                        break;
                    case 1:
                        tab.setText("Khoảng thời gian");
                        break;
                    case 2:
                        tab.setText("Tháng");
                        break;
                    case 3:
                        tab.setText("Vận chuyển");
                    break;
                }
            }
        }).attach();

    }
}