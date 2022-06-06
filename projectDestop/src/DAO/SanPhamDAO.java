/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BEAN.MatHang;
import DB.Connection_to_SQLServer;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class SanPhamDAO {

    public static List<MatHang> getAllSanPham() {

        List<MatHang> mathangs = new ArrayList<MatHang>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from matHang";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang mathang = new MatHang();

                mathang.setMaMH(rs.getString("MaMH"));
                mathang.setTenMH(rs.getString("TenMH"));
                mathang.setSoLuong(rs.getInt("SoLuong"));
                mathang.setLoai(rs.getInt("Loai"));
                mathang.setNhaSX(rs.getString("NhaSX"));
                mathang.setNgayTao(rs.getDate("NgayTao"));
                mathang.setGiaNhap(rs.getInt("GiaNhap"));
                mathang.setGiaBan(rs.getInt("giaBan"));
                mathang.setHinhAnh(rs.getBytes("hinhAnh"));
                mathang.setTrangThai(rs.getInt("trangThai"));

                mathangs.add(mathang);
            }
            close(conn, ps, rs);
            return mathangs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String[] getAllMaMH() {
        int i = 0;
        String[] listMaMH = new String[17];

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select maMH from matHang";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listMaMH[i] = rs.getString("MaMH");
                i++;
            }
            close(conn, ps, rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listMaMH;
    }

    public static void addSanPham(MatHang mathang) {

        java.util.Date ngayGio = mathang.getNgayTao();
        java.sql.Date sqlStartDate = new java.sql.Date(ngayGio.getTime());

        Connection conn = Connection_to_SQLServer.innit();
        String sql = "insert into matHang  values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, mathang.getMaMH());
            ps.setString(2, mathang.getTenMH());
            ps.setInt(3, mathang.getSoLuong());
            ps.setInt(4, mathang.getLoai());
            ps.setString(5, mathang.getNhaSX());
            ps.setDate(6, sqlStartDate);
            ps.setInt(7, mathang.getGiaNhap());
            ps.setInt(8, mathang.getGiaBan());
            ps.setBytes(9, mathang.getHinhAnh());

            int result = ps.executeUpdate();
            System.out.println(result);
            close(conn, ps, null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static MatHang getMaMH(String maMH) {
        Connection conn = Connection_to_SQLServer.innit();
        String sql = "select * from matHang where maMH =?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maMH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                MatHang mathang = new MatHang();
                mathang.setMaMH(rs.getString("MaMH"));
                mathang.setTenMH(rs.getString("TenMH"));
                mathang.setSoLuong(rs.getInt("SoLuong"));
                mathang.setLoai(rs.getInt("Loai"));
                mathang.setNhaSX(rs.getString("NhaSX"));
                mathang.setNgayTao(rs.getDate("NgayTao"));
                mathang.setGiaNhap(rs.getInt("GiaNhap"));
                mathang.setGiaBan(rs.getInt("giaBan"));
                mathang.setHinhAnh(rs.getBytes("hinhAnh"));
                return mathang;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
//      tìm kiếm sản phẩm theo tên

    public static MatHang searchProduct(String tenMH) {

        Connection conn = Connection_to_SQLServer.innit();
        String sql = "Select * from matHang where tenMH = ?";

        try {

            MatHang product = new MatHang();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenMH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product.setMaMH(rs.getString("MaMH"));
                product.setTenMH(rs.getString("TenMH"));
                product.setSoLuong(rs.getInt("SoLuong"));
                product.setLoai(rs.getInt("Loai"));
                product.setNhaSX(rs.getString("NhaSX"));
                product.setNgayTao(rs.getDate("NgayTao"));
                product.setGiaNhap(rs.getInt("GiaNhap"));
                product.setGiaBan(rs.getInt("giaBan"));
                product.setHinhAnh(rs.getBytes("hinhAnh"));

                return product;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
//    tìm kiếm sản phẩm theo maSP, tenSP, loại, giá nhập, giá bán, nhà sản xuất, tồn kho, ngày tạo

    public static List<MatHang> searchAllProduct(String keySearch) {
//        int i =1;
        List<MatHang> mathangs = new ArrayList<MatHang>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from matHang where MaMH like '%" + keySearch + "%' or TenMH like '%" + keySearch + "%' or NhaSX like '%" + keySearch + "%' or Loai like '%" + keySearch + "%'  or giaNhap like '%" + keySearch + "%'\n"
                + " or giaBan like '%" + keySearch + "%' or SoLuong like '%" + keySearch + "%' or NgayTao like '%" + keySearch + "%'";
        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang product = new MatHang();
                product.setMaMH(rs.getString("MaMH"));
                product.setTenMH(rs.getString("TenMH"));
                product.setSoLuong(rs.getInt("SoLuong"));
                product.setLoai(rs.getInt("Loai"));
                product.setNhaSX(rs.getString("NhaSX"));
                product.setNgayTao(rs.getDate("NgayTao"));
                product.setGiaNhap(rs.getInt("GiaNhap"));
                product.setGiaBan(rs.getInt("giaBan"));
                product.setTrangThai(rs.getInt("trangThai"));
                product.setHinhAnh(rs.getBytes("hinhAnh"));

                mathangs.add(product);
            }
            close(conn, ps, null);
            return mathangs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mathangs;
    }
//kiểm tra số lượng mua có lớn hơn lượng tồn kho hay không

//    mua dc = true;
//    k mua dc  false;
    public static boolean checkAmount(String maMH, int soLuong) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select soLuong from matHang where maMH = ?";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maMH);
            rs = ps.executeQuery();
            while(rs.next()){
            if (rs.getInt("soLuong") >= soLuong) {
                return true;
            }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static void updateMatHang(MatHang mathang) {
        Connection conn = Connection_to_SQLServer.innit();
        String sql = "Update matHang set tenMH =?, giaNhap =?, giaBan =?, hinhAnh = ? where maMH =?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mathang.getTenMH());
            ps.setInt(2, mathang.getGiaNhap());
            ps.setInt(3, mathang.getGiaBan());
            ps.setBytes(4, mathang.getHinhAnh());
            ps.setString(5, mathang.getMaMH());

            int result = ps.executeUpdate();
            System.out.println(result);
            close(conn, ps, null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteMatHang(String maMH) {
        Connection conn = Connection_to_SQLServer.innit();
        String sql = "delete from matHang where maMH =?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maMH);

            int result = ps.executeUpdate();
            System.out.println(result);
            close(conn, ps, null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

//    thay đổi trang thái của mặt hàng===> nó thay thế cho chức năng xóa trong CSDL
    public static void setStatus_product(String maMH) {
        Connection conn = Connection_to_SQLServer.innit();
        String sql = "UPDATE MatHang\n"
                + "SET trangThai =1\n"
                + "WHERE MaMH = '" + maMH + "'";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            int result = ps.executeUpdate();
            System.out.println(result);
            close(conn, ps, null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

//    lấy ra thông tin hóa đơn xuất 
    public static List<MatHang> getAllmatHang_on_Donhang(int maDH) {

        List<MatHang> mathangs = new ArrayList<MatHang>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select C.SoDH, A.TenMH, B.SoLuong, A.giaBan\n"
                + "from MatHang A, ChiTietDonHang B, DonHang C\n"
                + "where A.MaMH = B.MaMH and B.SoDH = C.SoDH and C.SoDH = ?\n"
                + "group by C.SoDH,A.TenMH, B.SoLuong, A.giaBan";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maDH);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang mathang = new MatHang();

                mathang.setTenMH(rs.getString("TenMH"));
                mathang.setSoLuong(rs.getInt("SoLuong"));
                mathang.setGiaBan(rs.getInt("giaBan"));
                mathangs.add(mathang);
            }
            close(conn, ps, rs);
            return mathangs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//     hiện thị sale 

    public static List<MatHang> getAllMatHangSale(int trangThai) {
        List<MatHang> mathangs = new ArrayList<MatHang>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select A.MaMH,A.TenMH, A.giaNhap, A.giaBan, (A.giaBan-A.giaNhap)*B.soluong as 'LN thuần', B.soluong, D.TrangThai\n"
                + "from mathang A, ChiTietDonHang B, DonHang C, TinhTrangDonHang D\n"
                + "where A.MaMH = B.MaMH and \n"
                + "B.SoDH = C.SoDH and \n"
                + "C.SoDH= D.SoDonHang and\n"
                + "D.TrangThai = ? \n"
                + "group by  A.MaMH, B.soluong, D.TrangThai, A.TenMH, A.giaNhap, A.giaBan";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, trangThai);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang mathang = new MatHang();

                mathang.setMaMH(rs.getString("MaMH"));
                mathang.setTenMH(rs.getString("TenMH"));
                mathang.setSoLuong(rs.getInt("SoLuong"));
//                lấy loại để khỏi khai báo 1 biến mới
//                  loiaj giờ sẽ có nhiệm vụ là trạng thái
                mathang.setLoai(rs.getInt("TrangThai"));
                if (trangThai == 1) {
                    mathang.setNhaSX("Đã giao");
                }
                if (trangThai == 2) {
                    mathang.setNhaSX("Đang vận chuyển");
                }
                if (trangThai == 3) {
                    mathang.setNhaSX("Đã hủy");
                }
                mathang.setGiaNhap(rs.getInt("GiaNhap"));
                mathang.setGiaBan(rs.getInt("giaBan"));
                mathangs.add(mathang);
            }
            close(conn, ps, rs);
            return mathangs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mathangs;
    }

    public static List<MatHang> get_doanhThu_Quy(int trangThai) {
        List<MatHang> mathangs = new ArrayList<MatHang>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select A.MaMH,A.TenMH, A.giaNhap, A.giaBan, (A.giaBan-A.giaNhap)*B.soluong as 'LN thuần', B.soluong, D.TrangThai\n"
                + "from mathang A, ChiTietDonHang B, DonHang C, TinhTrangDonHang D\n"
                + "where A.MaMH = B.MaMH and \n"
                + "B.SoDH = C.SoDH and \n"
                + "C.SoDH= D.SoDonHang and\n"
                + "D.TrangThai = ? \n"
                + "group by  A.MaMH, B.soluong, D.TrangThai, A.TenMH, A.giaNhap, A.giaBan";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, trangThai);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang mathang = new MatHang();

                mathang.setMaMH(rs.getString("MaMH"));
                mathang.setTenMH(rs.getString("TenMH"));
                mathang.setSoLuong(rs.getInt("SoLuong"));
//                lấy loại để khỏi khai báo 1 biến mới
//                  loiaj giờ sẽ có nhiệm vụ là trạng thái
                mathang.setLoai(rs.getInt("TrangThai"));
                if (trangThai == 1) {
                    mathang.setNhaSX("Đã giao");
                }
                if (trangThai == 2) {
                    mathang.setNhaSX("Đang vận chuyển");
                }
                if (trangThai == 3) {
                    mathang.setNhaSX("Đã hủy");
                }
                mathang.setGiaNhap(rs.getInt("GiaNhap"));
                mathang.setGiaBan(rs.getInt("giaBan"));

                mathangs.add(mathang);
            }
            close(conn, ps, rs);
            return mathangs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mathangs;
    }

//     sort == 0 => ASC        sort ==1 => DESC
//     dvSort ==0 => (A.giaBan-A.giaNhap)*B.soluong     dvSort ==1 => B.soluong
    public static List<MatHang> getAllMatHangSale2(int trangThai, int sort, int dvSort) {
        List<MatHang> mathangs = new ArrayList<MatHang>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String strdvSort = "";
        String strsort = "";
        if (sort == 0 && dvSort == 0) {
            strdvSort = "(A.giaBan-A.giaNhap)*B.soluong";
            strsort = "ASC";
        }
        if (sort == 0 && dvSort == 1) {
            strdvSort = "B.soluong";
            strsort = "ASC";
        }
        if (sort == 1 && dvSort == 0) {
            strdvSort = "(A.giaBan-A.giaNhap)*B.soluong";
            strsort = "DESC";
        }
        if (sort == 1 && dvSort == 1) {
            strdvSort = "B.soluong";
            strsort = "DESC";
        }

        String sql = "select A.MaMH,A.TenMH, A.giaNhap, A.giaBan, (A.giaBan-A.giaNhap)*B.soluong as 'LN thuần', B.soluong, D.TrangThai\n"
                + "from mathang A, ChiTietDonHang B, DonHang C, TinhTrangDonHang D\n"
                + "where A.MaMH = B.MaMH and \n"
                + "B.SoDH = C.SoDH and \n"
                + "C.SoDH= D.SoDonHang and\n"
                + "D.TrangThai =?\n"
                + "group by  A.MaMH, B.soluong, D.TrangThai, A.TenMH, A.giaNhap, A.giaBan\n"
                + "order by " + strdvSort + " " + strsort;

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, trangThai);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang mathang = new MatHang();

                mathang.setMaMH(rs.getString("MaMH"));
                mathang.setTenMH(rs.getString("TenMH"));
                mathang.setSoLuong(rs.getInt("SoLuong"));
//                lấy loại để khỏi khai báo 1 biến mới
//                  loiaj giờ sẽ có nhiệm vụ là trạng thái
                mathang.setLoai(rs.getInt("TrangThai"));
                if (trangThai == 1) {
                    mathang.setNhaSX("Đã giao");
                }
                if (trangThai == 2) {
                    mathang.setNhaSX("Đang vận chuyển");
                }
                if (trangThai == 3) {
                    mathang.setNhaSX("Đã hủy");
                }
                mathang.setGiaNhap(rs.getInt("GiaNhap"));
                mathang.setGiaBan(rs.getInt("giaBan"));
                mathangs.add(mathang);
            }
            close(conn, ps, rs);
            return mathangs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mathangs;
    }

    //     sort == 0 => ASC        sort ==1 => DESC
    public static List<MatHang> getMatHang_TonKho(int sort) {
        List<MatHang> mathangs = new ArrayList<MatHang>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strSort = "";
        if (sort == 0) {
            strSort = "ASC";
        }
        if (sort == 1) {
            strSort = "DESC";
        }
        String sql = "select A.MaMH,A.TenMH, A.giaNhap, A.giaBan , A.soluong, A.NgayTao\n"
                + "from mathang A \n"
                + "group by A.MaMH,A.TenMH, A.giaNhap, A.giaBan , A.soluong, A.NgayTao\n"
                + "order by  A.soluong " + strSort;

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang mathang = new MatHang();

                mathang.setMaMH(rs.getString("MaMH"));
                mathang.setTenMH(rs.getString("TenMH"));
                mathang.setSoLuong(rs.getInt("SoLuong"));
//                mathang.setLoai(rs.getInt("Loai"));
//                mathang.setNhaSX(rs.getString("NhaSX"));
                mathang.setNgayTao(rs.getDate("NgayTao"));
                mathang.setGiaNhap(rs.getInt("GiaNhap"));
                mathang.setGiaBan(rs.getInt("giaBan"));
//                mathang.setHinhAnh(rs.getBytes("hinhAnh"));

                mathangs.add(mathang);
            }
            close(conn, ps, rs);
            return mathangs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//     Doanh thu bán hàng

    public static int get_doanhThuBanHang(int time) {
        List<MatHang> mathangs = new ArrayList<MatHang>();

        int giaBan = 0;
        int soLuong = 0;
        int doanhthu = 0;
        int i = 0;
        Date thoiGian;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select A.MaMH, A.giaBan*B.soluong as 'LN thuần', B.soluong, D.TrangThai,A.giaBan, C.NgayDat\n"
                + "                from mathang A, ChiTietDonHang B, DonHang C, TinhTrangDonHang D\n"
                + "                where A.MaMH = B.MaMH and \n"
                + "                B.SoDH = C.SoDH and \n"
                + "                C.SoDH= D.SoDonHang and\n"
                + "                  D.TrangThai = 2 \n"
                + "                group by  A.MaMH, B.soluong, D.TrangThai, A.giaBan, C.NgayDat";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang mathang = new MatHang();

                mathang.setGiaBan(rs.getInt("giaBan"));
                mathang.setSoLuong(rs.getInt("soluong"));
                mathang.setNgayTao(rs.getDate("NgayDat"));

                mathangs.add(mathang);
            }
            for (MatHang mathang : mathangs) {
                if (FormatElement.formatYear(mathang.getNgayTao(), time)) {
                    doanhthu += mathang.getGiaBan() * mathang.getSoLuong();
                }
            }
            close(conn, ps, rs);

            return doanhthu;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return doanhthu;
    }
//     Giá vốn hàng bán

    public static int get_giaVonHangBan(int time) {
        List<MatHang> mathangs = new ArrayList<MatHang>();

        int giaMua = 0;
        int soLuong = 0;
        int giaVon = 0;
        int i = 0;
        Date thoiGian;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  select A.MaMH, B.soluong, D.TrangThai, A.giaNhap,  C.NgayDat\n"
                + "                from mathang A, ChiTietDonHang B, DonHang C, TinhTrangDonHang D\n"
                + "                where A.MaMH = B.MaMH and \n"
                + "                B.SoDH = C.SoDH and \n"
                + "                C.SoDH= D.SoDonHang and\n"
                + "                  D.TrangThai = 2\n"
                + "                group by  A.MaMH,  B.soluong, D.TrangThai,  A.giaNhap,  C.NgayDat";

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MatHang mathang = new MatHang();

                mathang.setGiaNhap(rs.getInt("giaNhap"));
                mathang.setSoLuong(rs.getInt("soLuong"));
                mathang.setNgayTao(rs.getDate("NgayDat"));

                mathangs.add(mathang);
            }
            for (MatHang mathang : mathangs) {
                if (FormatElement.formatYear(mathang.getNgayTao(), time)) {
                    giaVon += mathang.getGiaNhap() * mathang.getSoLuong();
                }
            }
            close(conn, ps, rs);
            return giaVon;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //        lợi nhuận gộp về bán hàng và cung cấp dịch vụ
    public static int loiNhuanUocTinh(int defaultyear) {
        int loinhuan_theoNam = get_doanhThuBanHang(defaultyear)
                - get_giaVonHangBan(defaultyear);
        return loinhuan_theoNam;
    }

//     kiểm tra sản phẩm có tồn tại hay không
    public static boolean checkSP(String maSP) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "select MaMH from matHang";
        ResultSet rs = null;

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (maSP.equalsIgnoreCase(rs.getString("maMH"))) {
                    return true;
                }
            }

            close(conn, ps, rs);
            return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    //     kiểm tra sản phẩm có tồn tại hay không
    public static boolean checkTrangThaiSP(String maSP) {
        Connection conn = null;
        PreparedStatement ps = null;
//        lấy ra những mặt hàng còn kinh doanh
        String sql = "select MaMH from matHang where trangThai =0";
        ResultSet rs = null;

        try {
            conn = Connection_to_SQLServer.innit();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (maSP.equalsIgnoreCase(rs.getString("maMH"))) {
                    return true;
                }
            }

            close(conn, ps, rs);
            return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
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
