/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BEAN.User;
import DB.Connection_to_SQLServer;
import static DAO.NhanVien_DAO.close;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class UserDao {
     public static int getUser(User user ) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
//        nếu tài khoản tồn tại chekcTK =1;
//              +   nếu chức vu trả về =1 => quản lý => check = 2
//              +   nếu chức vụ trả về = 2 => nhân viên  => check = 3
//      nếu tài khoản không tồn tại chekc =0;
        int checkTK =0;
        int maNV =0;
        String sql = "select * from Login";
        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
               if(user.getUserName().equals(rs.getString("username")) && user.getPassWord().equalsIgnoreCase(rs.getString("pass"))){
//                   phân quyền
                   int checkChucVu = rs.getInt("chucVu");
                   checkTK = 1 + checkChucVu;
//                   lấy ra mã nhân viên
                    if(checkTK == 3){  // nhân viên
                        maNV = rs.getInt("maNV");
                    }else{
                        maNV = -1; // admin
                    }
                   
      
//                   return checkTK;
                    break;
               }
            }
            System.out.println("Mã nhân viên"+ maNV);
            if(maNV !=0){
                close(conn, ps, rs);
                return maNV;
            }
            close(conn, ps, rs);
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return checkTK;
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
