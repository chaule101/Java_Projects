
package com.polypro.model;

/**
 *
 * @author Chau Le
 */
public class NhanVien {

    private String maNV;
    private String gioiTinh;
    private String matKhau;
    private String hoTen;
    private String dienThoai;
    private String email;
    private String diaChi;
    private String hinh;
    private boolean vaiTro = false;

    @Override
    public String toString() {
        return this.hoTen;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public boolean getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public NhanVien(String maNV, String gioiTinh, String matKhau, String hoTen, String dienThoai, String email, String diaChi, String hinh) {
        this.maNV = maNV;
        this.gioiTinh = gioiTinh;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.hinh = hinh;
    }
    

  
    public NhanVien() {

    }
}
