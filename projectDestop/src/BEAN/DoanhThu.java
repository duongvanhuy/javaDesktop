/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

/**
 *
 * @author dell
 */
public class DoanhThu {
    private int STT;
    private String maSP;
    private String tenSP;
    private int soLuong;
    private String giaNhap;
    private String giaBan;
    private String trangThai;
//    private String ngayTao;
    private String loiNhuan;
    private String chuThich;

    public DoanhThu(int STT, String maSP, String tenSP, int soLuong, String giaNhap, String giaBan, String trangThai, String loiNhuan, String chuThich) {
        this.STT = STT;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.loiNhuan = loiNhuan;
        this.chuThich = chuThich;
    }
    
    

    public String getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(String giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(String loiNhuan) {
        this.loiNhuan = loiNhuan;
    }

   

    
    
    public int getSTT() {
        return STT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }



    public DoanhThu() {
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }


  


    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }
    
    
    
}
