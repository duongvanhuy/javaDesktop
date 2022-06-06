/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import javax.swing.JTable;
import BEAN.DoanhThu;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class getvaluetable {
    
    static FormatElement formatElement =  new FormatElement();
//    lấy toàn bộ giá trị cảu table
    public static int[] getvalueTable_chart(javax.swing.JTable tableName){
         //          lấy ra hàng đang chọn và cột đang chọn => mã mặt hàng
//         row-column
//         0,1-----------2,1------------5,1
//          0,2-----------2,2------------5,2
//          0,3-----------2,3------------5,3
//          0,4-----------2,4------------5,4
            int[] valueTable = new int[12];
            int k =1;
            int l =0;
            int m =0;
//            khai báo mảng 2 chiều vs các chỉ số đầu định nghĩa 1:1
            for(int i = 1; i <= 3; i++){
                for(int j =1; j<= 4; j++){
                    String chuoiCanTach = String.valueOf(tableName.getValueAt(l,j));
                    valueTable[m] =  formatElement.converMoney_int(chuoiCanTach);
                     System.out.println(formatElement.converMoney_int(chuoiCanTach));
                        m++;
                }
                if(l==0){
                    l = l +2;
                }else{
                    l = l+ 3;
                }
            }
           return  valueTable;
    }
    
//    get all value table
    public static List<DoanhThu> getValuedoanhThu_table(javax.swing.JTable tableName){
        List<DoanhThu> doanhThus = new ArrayList<DoanhThu>();
            int countRows = tableName.getRowCount();
            for(int i = 0; i < countRows; i++){
                    DoanhThu doanhthu = new DoanhThu();
              
                    doanhthu.setSTT((int) tableName.getValueAt(i,0));
                    doanhthu.setMaSP((String) tableName.getValueAt(i,1));
                    doanhthu.setTenSP((String) tableName.getValueAt(i,2));
                    doanhthu.setSoLuong((int) tableName.getValueAt(i,3));
                    doanhthu.setTrangThai((String) tableName.getValueAt(i, 4));
                    doanhthu.setGiaNhap((String) tableName.getValueAt(i,5));
                    doanhthu.setGiaBan((String) tableName.getValueAt(i,6));
                    doanhthu.setLoiNhuan((String) tableName.getValueAt(i,7));
                    doanhthu.setChuThich((String) tableName.getValueAt(i,8));
                    
                    doanhThus.add(doanhthu);
            }
           return  doanhThus;
    }

}
