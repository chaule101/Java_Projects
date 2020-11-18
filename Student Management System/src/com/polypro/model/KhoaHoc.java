/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.model;

import com.polypro.helper.DateHelper;
import java.util.Date;

/**
 *
 * @author Chau Le
 */
public class KhoaHoc {

    private int maKH;
    private String maCD;
    private double hocPhi;
    private int thoiLuong;
    private Date ngayKG;
    private String ghiChu;
    private String maNV;
    private Date ngayTao = DateHelper.now();

    @Override
    public String toString() {
        return this.maCD + " (" + this.ngayKG + ")";
    }

    public int getMaKH() {
        return maKH;
    }

    public String getMaCD() {
        return maCD;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public Date getNgayKG() {
        return ngayKG;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public String getMaNV() {
        return maNV;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public void setNgayKG(Date ngayKG) {
        this.ngayKG = ngayKG;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public KhoaHoc() {

    }
}
