package com.example.quanly.model;

public class HoaDonOuter {
    private String idHoaDon, usernguoimua , ngayMua, tongTien, ten, sdt, diaChi;

    public HoaDonOuter(String idHoaDon, String usernguoimua, String ngayMua, String tongTien, String ten, String sdt, String diaChi) {
        this.idHoaDon = idHoaDon;
        this.usernguoimua = usernguoimua;
        this.ngayMua = ngayMua;
        this.tongTien = tongTien;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getUsernguoimua() {
        return usernguoimua;
    }

    public void setUsernguoimua(String usernguoimua) {
        this.usernguoimua = usernguoimua;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
