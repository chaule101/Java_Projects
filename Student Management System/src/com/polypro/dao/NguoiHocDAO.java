/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.dao;

import com.polypro.helper.DateHelper;
import com.polypro.helper.JdbcHelper;
import com.polypro.model.NguoiHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class NguoiHocDAO {

    public void insert(NguoiHoc model) {
         
        String sql = "INSERT INTO NguoiHoc (MaNH, HoTen, GioiTinh, NgaySinh, "
                + "Email, DienThoai, GhiChu, Hinh, MaNV) VALUES (?, N?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNH(),
                model.getHoTen(),
//                model.isGioiTinh(),
                model.getGioiTinh(),
                //Phải set thời gian giống bên Database mới ko bị lỗi
                DateHelper.toString(model.getNgaySinh(), "yyyy-MM-dd"),             
                model.getEmail(),
                model.getDienThoai(),
                model.getGhiChu(),
                model.getHinh(),
                model.getMaNV());
    }

    public void update(NguoiHoc model) {
        String sql = "UPDATE NguoiHoc SET HoTen=?, GioiTinh=?, NgaySinh=?, Email=?, "
                + "DienThoai=?, GhiChu=?, Hinh=?, MaNV=? WHERE MaNH=?";
        JdbcHelper.executeUpdate(sql,
                model.getHoTen(),
//                model.isGioiTinh(),
                model.getGioiTinh(),
                DateHelper.toString(model.getNgaySinh(), "yyyy-MM-dd"),
                model.getEmail(),
                model.getDienThoai(),
                model.getGhiChu(),
                model.getHinh(),
                model.getMaNV(),
                model.getMaNH());
    }

    public void delete(String id) {
        String sql = "DELETE FROM NguoiHoc WHERE MaNH=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<NguoiHoc> select() {
        String sql = "SELECT * FROM NguoiHoc";
        return select(sql);
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public List<NguoiHoc> selectByCourse(Integer makh) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return select(sql, makh);
    }

    public NguoiHoc findById(String manh) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH=?";
        List<NguoiHoc> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NguoiHoc> select(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NguoiHoc model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private NguoiHoc readFromResultSet(ResultSet rs) throws SQLException {
        NguoiHoc model = new NguoiHoc();
        model.setMaNH(rs.getString("MaNH"));
        model.setHoTen(rs.getString("HoTen"));
//        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setGioiTinh(rs.getString("GioiTinh"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setEmail(rs.getString("Email"));
        model.setDienThoai(rs.getString("DienThoai"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setHinh(rs.getString("Hinh"));
        model.setMaNV(rs.getString("MaNV"));
        model.setNgayDK(rs.getDate("NgayDK"));
        return model;
    }
}
