package com.example.quanly.model;

public class TheLoaiSoLuong {
    String ten;
    int soluong;

    public TheLoaiSoLuong() {
    }

    public TheLoaiSoLuong(String ten, int soluong) {
        this.ten = ten;
        this.soluong = soluong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "TheLoaiSoLuong{" +
                "ten='" + ten + '\'' +
                ", soluong=" + soluong +
                '}';
    }
}
