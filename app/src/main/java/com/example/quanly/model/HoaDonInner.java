package com.example.quanly.model;

public class HoaDonInner {
    private String tieuDe, soLuong, tienSach, hinhAnh;

    public HoaDonInner(String tieuDe, String soLuong, String tienSach, String hinhAnh) {
        this.tieuDe = tieuDe;
        this.soLuong = soLuong;
        this.tienSach = tienSach;
        this.hinhAnh = hinhAnh;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTienSach() {
        return tienSach;
    }

    public void setTienSach(String tienSach) {
        this.tienSach = tienSach;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
