/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.model;

import com.polypro.helper.DateHelper;
import java.util.Date;


public class NguoiHoc {

    private String maNH;
    private String hoTen;
//    private boolean gioiTinh;
//    private byte gioiTinh;
   private String gioiTinh;
    private Date ngaySinh;
    private String email;
    private String dienThoai;
    private String ghiChu;
    private String hinh;
    private String maNV;
    private Date ngayDK = DateHelper.now();
    

    @Override
    public String toString() {
        return this.hoTen;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

//    public boolean isGioiTinh() {
//        return gioiTinh;
//    }
//
//    public void setGioiTinh(boolean gioiTinh) {
//        this.gioiTinh = gioiTinh;
//    }

//    public byte getGioiTinh() {
//        return gioiTinh;
//    }
//
//    public void setGioiTinh(byte gioiTinh) {
//        this.gioiTinh = gioiTinh;
//    }
    
    
    
    
    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    

//    public NguoiHoc(String maNH, String hoTen, byte gioiTinh, Date ngaySinh, String email, String dienThoai, String ghiChu, String hinh, String maNV) {
//        this.maNH = maNH;
//        this.hoTen = hoTen;
//        this.gioiTinh = gioiTinh;
//        this.ngaySinh = ngaySinh;
//        this.email = email;
//        this.dienThoai = dienThoai;
//        this.ghiChu = ghiChu;
//        this.hinh = hinh;
//        this.maNV = maNV;
//    }
//    
//    
    

//    public NguoiHoc(String maNH, String hoTen, boolean gioiTinh, Date ngaySinh, String email, String dienThoai, String ghiChu, String hinh, String maNV) {
//        this.maNH = maNH;
//        this.hoTen = hoTen;
//        this.gioiTinh = gioiTinh;
//        this.ngaySinh = ngaySinh;
//        this.email = email;
//        this.dienThoai = dienThoai;
//        this.ghiChu = ghiChu;
//        this.hinh = hinh;
//        this.maNV = maNV;
//    }

    public NguoiHoc() {
    }

    public NguoiHoc(String maNH, String hoTen, String gioiTinh, Date ngaySinh, String email, String dienThoai, String ghiChu, String hinh, String maNV) {
        this.maNH = maNH;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.dienThoai = dienThoai;
        this.ghiChu = ghiChu;
        this.hinh = hinh;
        this.maNV = maNV;
    }
    

}
