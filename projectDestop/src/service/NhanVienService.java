
package service;

import DAO.NhanVien_DAO;
import java.util.List;
import BEAN.NhanVien;

public class NhanVienService {

    public static int getTongLuong() {
        return NhanVien_DAO.getTongLuong();
    }

    public static List<NhanVien> getAll_NhanVien() {
        return NhanVien_DAO.getAll_NhanVien();
    }

    public static NhanVien getNhanVien_MaNV(int maNV) {
        return NhanVien_DAO.getNhanVien_MaNV(maNV);
    }
}
