/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BEAN.NhanVien;
import DB.Connection_to_SQLServer;

/**
 *
 * @author dell
 */
public class NhanVien_DAO {
     //    danh sách nhân viên
    public static List<NhanVien> getAll_NhanVien(){
        List<NhanVien> nhanViens = new ArrayList<NhanVien>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        

        String sql = "select * from NhanVien";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();

                    nhanVien.setMaNV(rs.getInt("maNV"));
                    nhanVien.setHoTen(rs.getString("hoTen"));
                    nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
                    nhanVien.setDiaChi(rs.getString("diaChi"));
                    nhanVien.setChucVu(rs.getInt("chucVu"));
                    nhanVien.setLuong(rs.getInt("luong"));
                    nhanVien.setChiNhanh(rs.getInt("chiNhanh"));
                    nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                    nhanVien.setNgayTao(rs.getDate("ngayTao"));

                nhanViens.add(nhanVien);
            }
            close(conn, ps, rs);
            return nhanViens;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
//    tổng lương của toàn nhân viên
     public static int getTongLuong(){
       

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int tongLuong =0;

        String sql = "select sum(luong) as N'tongLuong'\n" +
"from NhanVien";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                tongLuong = rs.getInt("tongLuong");
            }
            close(conn, ps, rs);
            return tongLuong;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
//  lấy ra nhân viên theo mã nhân viên
     public static NhanVien getNhanVien_MaNV(int maNV){
         NhanVien nhanVien = new NhanVien();
         
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
   
        String sql = "select * from NhanVien where maNV = ?";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maNV);
            rs = ps.executeQuery();

            while (rs.next()) {
                    nhanVien.setMaNV(rs.getInt("maNV"));
                    nhanVien.setHoTen(rs.getString("hoTen"));
                    nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
                    nhanVien.setDiaChi(rs.getString("diaChi"));
                    nhanVien.setChucVu(rs.getInt("chucVu"));
                    nhanVien.setLuong(rs.getInt("luong"));
                    nhanVien.setChiNhanh(rs.getInt("chiNhanh"));
                    nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                    nhanVien.setNgayTao(rs.getDate("ngayTao"));
            }
            close(conn, ps, rs);
            return nhanVien;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
     }
    
    
       public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
