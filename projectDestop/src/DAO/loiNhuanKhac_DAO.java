/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.SanPhamDAO.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BEAN.LoiNhuanKhac;
import DB.Connection_to_SQLServer;

/**
 *
 * @author dell
 */
public class loiNhuanKhac_DAO {
    
//    lấy ra lợi nhuận theo năm nhập vào
    public static int get_LoiNhuan(int index_nam){
//        List<loiNhuanKhac> loiNhuans = new ArrayList<loiNhuanKhac>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int loiNhuan =0;

        String sql = "select value_Money from loiNhuanKhac where index_nam = " + index_nam;

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

  
              loiNhuan = rs.getInt("value_Money");
               

             
            }
            close(conn, ps, rs);
            return loiNhuan;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    
    //    lấy ra lợi nhuận theo năm nhập vào
    public static List<LoiNhuanKhac> getAll_LoiNhuan(){
        List<LoiNhuanKhac> loiNhuans = new ArrayList<LoiNhuanKhac>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        

        String sql = "select * from loiNhuanKhac";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoiNhuanKhac loinhuan = new LoiNhuanKhac();

            loinhuan.setIndex_nam(rs.getInt("index_nam"));
               loinhuan.setValue_Money(rs.getInt("value_Money"));
               loinhuan.setInformation(rs.getString("information"));

                loiNhuans.add(loinhuan);
            }
            close(conn, ps, rs);
            return loiNhuans;
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
