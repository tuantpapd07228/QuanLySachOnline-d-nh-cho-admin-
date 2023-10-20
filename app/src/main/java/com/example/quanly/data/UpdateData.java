package com.example.quanly.data;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateData {
    Context context;
    String urlxulidonhangcanxacnhan = GetIP.IP+":8686/quanly/update_xacnhan_hoadon.php";
    String urlvanchuyen = GetIP.IP+":8686/quanly/update_vanchuyen.php";

    public UpdateData(Context context) {
        this.context = context;
    }

    public void updateDonHangDoiXacNhan(String iddonhang, String trangthai){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlxulidonhangcanxacnhan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("idhoadon", iddonhang);
                map.put("trangthai", trangthai);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void updateDonHangVanChuyenThanhCong(String iddonhang, String trangthai){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlvanchuyen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("idhoadon", iddonhang);
                map.put("trangthai", trangthai);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

}
