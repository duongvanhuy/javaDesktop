/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.loiNhuanKhac_DAO;
import java.util.List;
import BEAN.LoiNhuanKhac;

/**
 *
 * @author dell
 */
public class LoiNhuanService {
    public static List<LoiNhuanKhac> getAll_LoiNhuanKhac(){
        return loiNhuanKhac_DAO.getAll_LoiNhuan();
    }
     public static int get_LoiNhuan(int index_nam){
         return loiNhuanKhac_DAO.get_LoiNhuan(index_nam);
     }
}
