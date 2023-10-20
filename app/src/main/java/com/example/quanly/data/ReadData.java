package com.example.quanly.data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanly.HomeActivity;
import com.example.quanly.model.HoaDonInner;
import com.example.quanly.model.HoaDonOuter;
import com.example.quanly.model.Sach;
import com.example.quanly.model.TheLoaiSoLuong;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadData {
    public ReadData(Context context) {
        this.context = context;
    }

    Context context;

    String urlchecklogin = GetIP.IP+ ":8686/quanly/checklogin.php";
    String urlhoadoninner = GetIP.IP+":8686/duanmau/get_HoaDoninner.php";
    String urlhoadonouter = GetIP.IP+":8686/quanly/get_hoadonouter.php";
    String urlgethoadonngaythang = GetIP.IP+":8686/quanly/get_hoadontheongaythang.php";
    String urlhoadondoixacnhan = GetIP.IP+":8686/quanly/get_hoadondoixacnhan.php";
    String urldonhangdangvanchuyen = GetIP.IP+":8686/quanly/get_donhangdangvanchuyen.php";
    String urlthongkedoanhthu = GetIP.IP+":8686/quanly/get_hoadontheongaythangouter.php";
    String urltongsachdaban = GetIP.IP+":8686/quanly/get_tongsosachdaban.php";
    String urltongsachdangvanchuyen = GetIP.IP+":8686/quanly/get_tongsachdangvanchuyen.php";
    String urlsach= GetIP.IP+ ":8686/duanmau/get_sach.php";
    String urltheloai= GetIP.IP + ":8686/duanmau/get_TheLoaiSach.php";


    //    String url
    // check login
    public void getModel(ArrayList<HoaDonOuter> arr, JSONObject jsonObject){
        try{
            arr.add(new HoaDonOuter(
                    jsonObject.getString("idHoaDon"),
                    jsonObject.getString("username"),
                    jsonObject.getString("ngayMua"),
                    jsonObject.getString("tongTien"),
                    jsonObject.getString("ten"),
                    jsonObject.getString("sdt"),
                    jsonObject.getString("diachi")));
        }catch (Exception e){}
    }
    public void checkLogin(String username,String password){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlchecklogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0){
                    Toast.makeText(context, "Login thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra("hoten",response);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, "Sai tai khoan hoac mat khau", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Sai tai khoan hoac mat khau", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("email", username);
                map.put("password", password);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    // get hoa don inner

    public interface XuLiHoaDonInner {void xulihoadoninner(ArrayList<HoaDonInner> arrhoadon1);}
    public void getHoaDoninner(String idhoadon , XuLiHoaDonInner xuLiHoaDon2 ){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlhoadoninner, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<HoaDonInner> arr = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        arr.add(new HoaDonInner(
                                jsonObject.getString("tieuDe"),
                                jsonObject.getString("soLuongMua"),
                                jsonObject.getString("tienSach"),
                                jsonObject.getString("hinhAnh")));
                    }
                    xuLiHoaDon2.xulihoadoninner(arr);
                } catch (JSONException e) {
                    Log.e("errr read 93 ",e.getMessage() );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errr read 99 ",error.getMessage() );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("idhoadon", idhoadon);
                return map;
            }
        };
        requestQueue.add(stringRequest);

    }
    // get hoa don outer
    public interface XuLiHoaDonOuter{void xulihoadonouter(ArrayList<HoaDonOuter > arrayList);}
    public void getHoaDonOuter(XuLiHoaDonOuter xuLiHoaDonOuter){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlhoadonouter, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<HoaDonOuter> arrayList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        arrayList.add(new HoaDonOuter(
                                jsonObject.getString("idHoaDon"),
                                jsonObject.getString("username"),
                                jsonObject.getString("ngayMua"),
                                jsonObject.getString("tongTien"),
                                jsonObject.getString("ten"),
                                jsonObject.getString("sdt"),
                                jsonObject.getString("diachi")));
//                        arrayList.add(new HoaDonOuter(
//                                jsonObject.getString("idHoaDon"),
//                                jsonObject.getString("username"),
//                                jsonObject.getString("ngayMua"),
//                                jsonObject.getString("tongTien"),
//                                jsonObject.getString("sdt"),
//                                jsonObject.getString("diachi")));
                        } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                xuLiHoaDonOuter.xulihoadonouter(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERR read 142", error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    //get hoa don theo ngay thang nam
    public interface XuLiHoaDonOuterNgayThang{void xulihoadonouterngaythang(ArrayList<HoaDonOuter > arrayList);}
    public void getHoaDonNgayThang(String ngay, XuLiHoaDonOuterNgayThang xuLiHoaDonOuterNgayThang){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlgethoadonngaythang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<HoaDonOuter> arrayList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        arrayList.add(new HoaDonOuter(
                                jsonObject.getString("idHoaDon"),
                                jsonObject.getString("username"),
                                jsonObject.getString("ngayMua"),
                                jsonObject.getString("tongTien"),
                                jsonObject.getString("ten"),
                                jsonObject.getString("sdt"),
                                jsonObject.getString("diachi")));
//                        arrayList.add(new HoaDonOuter(
//                                jsonObject.getString("idHoaDon"),
//                                jsonObject.getString("username"),
//                                jsonObject.getString("ngayMua"),
//                                jsonObject.getString("tongTien"),
//                                jsonObject.getString("sdt"),
//                                jsonObject.getString("diachi")));
                    }
                    xuLiHoaDonOuterNgayThang.xulihoadonouterngaythang(arrayList);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRRRRRRRRR read 175", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String >map = new HashMap<>();
                map.put("ngay", ngay);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getHoaDonNgayThang(String dauthang, String cuoithang, XuLiHoaDonOuterNgayThang xuLiHoaDonOuterNgayThang){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlgethoadonngaythang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<HoaDonOuter> arrayList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        arrayList.add(new HoaDonOuter(
                                jsonObject.getString("idHoaDon"),
                                jsonObject.getString("username"),
                                jsonObject.getString("ngayMua"),
                                jsonObject.getString("tongTien"),
                                "","",""));
                    }
                    xuLiHoaDonOuterNgayThang.xulihoadonouterngaythang(arrayList);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRRRRRRRRR read 175", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String >map = new HashMap<>();
                map.put("dauthang", dauthang);
                map.put("cuoithang", cuoithang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    // get don hang cho xac nhan
    public interface XuLiHoaDonDoiXacNhan{void xulihoadondoixacnhan(ArrayList<HoaDonOuter > arrayList);}
    public void getHoaDonDoiXacNhan(XuLiHoaDonDoiXacNhan xuLiHoaDondoixacnhan){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlhoadondoixacnhan, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<HoaDonOuter> arrayList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        arrayList.add(new HoaDonOuter(
                                jsonObject.getString("idHoaDon"),
                                jsonObject.getString("username"),
                                jsonObject.getString("ngayMua"),
                                jsonObject.getString("tongTien"),
                                jsonObject.getString("ten"),
                                jsonObject.getString("sdt"),
                                jsonObject.getString("diachi")));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                xuLiHoaDondoixacnhan.xulihoadondoixacnhan(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERR read 142", error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    // get don hang dang van chuyen
    public interface XuLiHoaDonVanChuyen{void xulihoadonvanchuyen(ArrayList<HoaDonOuter > arrayList);}
    public void getHoaDonVanChuyen(XuLiHoaDonVanChuyen xuLiHoaDondvanchuyen){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urldonhangdangvanchuyen, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<HoaDonOuter> arrayList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        arrayList.add(new HoaDonOuter(
                                jsonObject.getString("idHoaDon"),
                                jsonObject.getString("username"),
                                jsonObject.getString("ngayMua"),
                                jsonObject.getString("tongTien"),
                                jsonObject.getString("ten"),
                                jsonObject.getString("sdt"),
                                jsonObject.getString("diachi")));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                xuLiHoaDondvanchuyen.xulihoadonvanchuyen(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERR read 142", error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    // thong ke hoa don theo ngay thang
    public interface XuLiDoanhThu{void xulidoanhthu(ArrayList<HoaDonOuter> arhoadondoanhthu);}

    public void thongKeDoanhThu(String ngay, String dauthang, String cuoithang, String trangthai, XuLiDoanhThu xuLiDoanhThu){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlthongkedoanhthu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<HoaDonOuter> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        arrayList.add(new HoaDonOuter(
                                jsonObject.getString("idHoaDon"),
                                jsonObject.getString("username"),
                                jsonObject.getString("ngayMua"),
                                jsonObject.getString("tongTien"),
                                "","",""));
                    }
                    xuLiDoanhThu.xulidoanhthu(arrayList);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("read er 319", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("ngay", ngay);
                map.put("dauthang", dauthang);
                map.put("cuoithang", cuoithang);
                map.put("trangthai", trangthai);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    // get tong sach da ban
    public interface XuLiTongSachDaBan{void xuliSachDaBan(int tongsach);}

    public void getTongSachDaBan(String ngay, String dauthang, String cuoithang, XuLiTongSachDaBan xuLiTongSachDaBan){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urltongsachdaban, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("") || response == null) xuLiTongSachDaBan.xuliSachDaBan(0);
                else {
                    String str = response;
                    xuLiTongSachDaBan.xuliSachDaBan(Integer.parseInt(str));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("read er 349", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("ngaymua", ngay);
                map.put("dauthang", dauthang);
                map.put("cuoithang", cuoithang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    // tong sach dang van chuyen
    public void getTongSachDangVanChuyen( XuLiTongSachDaBan xuLiTongSachDaBan){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urltongsachdangvanchuyen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("") || response == null) xuLiTongSachDaBan.xuliSachDaBan(0);
                else {
                    String str = response;
                    xuLiTongSachDaBan.xuliSachDaBan(Integer.parseInt(str));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("read er 349", error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }
    //get toan bo sach
    public interface XuLiSauKhiLayDataSach{void xulisaukhilaydatasach(ArrayList<Sach> arr);}
    public void getSach(XuLiSauKhiLayDataSach xuLiSauKhiLayDataSach){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlsach, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Sach> arr = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Sach sach = new Sach(
                                jsonObject.getString("IDSach"),
                                jsonObject.getString("MaTheLoai"),
                                jsonObject.getString("TieuDe"),
                                jsonObject.getString("TacGia"),
                                jsonObject.getString("NXB"),
                                jsonObject.getString("MoTa"),
                                jsonObject.getInt("GiaBan"),
                                jsonObject.getInt("SoLuong"),
                                jsonObject.getString("LinkHinhAnh"),
                                jsonObject.getString("tenTheLoai"));
                        arr.add(sach);
                    } catch (JSONException e) {
                        Log.e("ERRRRRRRRR Sach 1",e.getMessage());
                    }
                }
                xuLiSauKhiLayDataSach.xulisaukhilaydatasach(arr);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRRRRRRRR Sach read 426 ",error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    // ghi list thể loại sách va so luong
    public interface XuLiTheLoai{void xulitheloai(ArrayList<TheLoaiSoLuong> arr);}
    public void getTheLoaiSach(XuLiTheLoai xuLiTheLoai){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urltheloai, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<TheLoaiSoLuong> arr = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        TheLoaiSoLuong t = new TheLoaiSoLuong(
                                jsonObject.getString("tenTheLoai"),
                                jsonObject.getInt("soLuong")
                        );
                        arr.add(t);

                    } catch (JSONException e) {
                        Log.e("ERRRRRRRRR the loai sach 1",e.getMessage());
                    }
                }
                xuLiTheLoai.xulitheloai(arr);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRRRRRRRR theloaisach 2",error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
