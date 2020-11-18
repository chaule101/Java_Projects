/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.dao;

import com.polypro.helper.JdbcHelper;
import com.polypro.model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chau Le
 */
public class NhanVienDAO {
    
    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        JdbcHelper.executeUpdate(sql, 
                model.getMaNV(),
                model.getGioiTinh(),
                model.getMatKhau(),
                model.getHoTen(),
                model.getDienThoai(),
                model.getEmail(),
                model.getDiaChi(),
                model.getHinh(),
                model.getVaiTro()
                );
    }
    
    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET GioiTinh=?, MatKhau=?, HoTen=?, DienThoai=?, Email=?, DiaChi=?, Hinh=?, VaiTro=? WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql,
                model.getGioiTinh(),
                model.getMatKhau(),                
                model.getHoTen(),
                model.getDienThoai(),
                model.getEmail(),
                model.getDiaChi(),
                model.getHinh(),
                model.getVaiTro(),
                model.getMaNV()
                );
    }
    
    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }
    
    public List<NhanVien> select() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }
    
    public NhanVien findByID(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = select(sql, maNV);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NhanVien> select(String sql, Object...args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                    NhanVien model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    private NhanVien readFromResultSet(ResultSet rs) throws SQLException{
        NhanVien model= new NhanVien();
        model.setMaNV(rs.getString("MaNV"));
        model.setGioiTinh(rs.getString("GioiTinh"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setHoTen(rs.getString("HoTen"));
        model.setDienThoai(rs.getString("DienThoai"));
        model.setEmail(rs.getString("Email"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setHinh(rs.getString("Hinh"));
        model.setVaiTro(rs.getBoolean("VaiTro")); 
        return model;
    }
}
