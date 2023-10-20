package com.example.quanly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.quanly.data.ReadData;
import com.example.quanly.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ReadData readData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checRemember();
        readData = new ReadData(LoginActivity.this);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData.checkLogin(binding.username.getText().toString().trim(), binding.password.getText().toString().trim());
                RememberUS(binding.username.getText().toString().trim(),binding.password.getText().toString().trim());
            }
        });


    }
    private void checRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("rm", false);
        if (check){
            String us = sharedPreferences.getString("us", null);
            String pw =  sharedPreferences.getString("pw", null);
            binding.username.setText(us);
            binding.password.setText(pw);
            binding.chkghinho.setChecked(true);
        }
    }

    private void RememberUS(String us , String pw){
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("us", us);
        editor.putString("pw", pw);
        editor.putBoolean("rm", true);
        editor.commit();
    }
}