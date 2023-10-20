package com.example.quanly.model;

import java.io.Serializable;

public class Sach implements Serializable {
    public String idSach, maTheLoai, tieuDe, tacGia, nxb,mota, hinh , tenTheLoai;
    public int giaBan,soLuong;

    public Sach() {
    }

    public Sach(String idSach, String maTheLoai, String tieuDe, String tacGia, String nxb, String mota, int giaBan, int soLuong, String hinh, String tenTheLoai) {
        this.idSach = idSach;
        this.maTheLoai = maTheLoai;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.nxb = nxb;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.mota = mota;
        this.hinh = hinh;
        this.tenTheLoai = tenTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
