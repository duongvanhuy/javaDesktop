/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author dell
 */
public class PhieuNhapTest {
    
    public PhieuNhapTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getSoPhieu method, of class PhieuNhap.
     */
    @Test
    public void testGetSoPhieu() {
        PhieuNhap instance = new PhieuNhap();
        int expResult = 0;
        int result = instance.getSoPhieu();
        assertEquals(expResult, result);
    }
    /**
     * Test of setSoPhieu method, of class PhieuNhap.
     */
    @Test
    public void testSetSoPhieu() {
        System.out.println("setSoPhieu");
        int soPhieu = -1;
        PhieuNhap instance = new PhieuNhap();
        instance.setSoPhieu(soPhieu);
       Exception ex = assertThrows(Exception.class, () -> instance.setSoPhieu(soPhieu));
    }

    /**
     * Test of getTongSL method, of class PhieuNhap.
     */
    @Test
    public void testGetTongSL() {

        PhieuNhap instance = new PhieuNhap();
        int expResult = 0;
        int result = instance.getTongSL();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTongSL method, of class PhieuNhap.
     */
    @Test
    public void testSetTongSL() {
     
        int tongSL = -10;
        PhieuNhap instance = new PhieuNhap();
        instance.setTongSL(tongSL);
         Exception ex = assertThrows(Exception.class, () ->  instance.setTongSL(tongSL));
    }

    /**
     * Test of getNhaSX method, of class PhieuNhap.
     */
    @Test
    public void testGetNhaSX() {
 
        PhieuNhap instance = new PhieuNhap();
        String expResult = "";
        String result = instance.getNhaSX();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNhaSX method, of class PhieuNhap.
     */
    @Test
    public void testSetNhaSX() {
        System.out.println("setNhaSX");
        String nhaSX = "";
        PhieuNhap instance = new PhieuNhap();
        instance.setNhaSX(nhaSX);
  
    }

    /**
     * Test of getNgayNhap method, of class PhieuNhap.
     */
    @Test
    public void testGetNgayNhap() {

        PhieuNhap instance = new PhieuNhap();
        Date expResult = null;
        Date result = instance.getNgayNhap();
        assertEquals(expResult, result);
    
    }

    /**
     * Test of setNgayNhap method, of class PhieuNhap.
     */
    @Test
    public void testSetNgayNhap() {
        Date ngayNhap = null;
        PhieuNhap instance = new PhieuNhap();
        instance.setNgayNhap(ngayNhap);
 
    }

    /**
     * Test of getNhanVien method, of class PhieuNhap.
     */
    @Test
    public void testGetNhanVien() {
 
        PhieuNhap instance = new PhieuNhap();
        int expResult = 0;
        int result = instance.getNhanVien();
        assertEquals(expResult, result);
  
    }

    /**
     * Test of setNhanVien method, of class PhieuNhap.
     */
    @Test
    public void testSetNhanVien() {

        int nhanVien = 0;
        PhieuNhap instance = new PhieuNhap();
        instance.setNhanVien(nhanVien);

    }
    
}
