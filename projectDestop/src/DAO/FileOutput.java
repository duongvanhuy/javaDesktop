/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import BEAN.DoanhThu;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author dell
 */
public class FileOutput {
    private static CellStyle cellStyleFormatNumber = null;

    //	lấy dữ liệu từ file excel
    public static void importExcel() throws IOException {

        FileInputStream file1 = null;
        HSSFWorkbook wb = null;
        try {
            file1 = new FileInputStream("D://test.xls");
            wb = new HSSFWorkbook(new POIFSFileSystem(file1));
            Sheet sheet = wb.getSheetAt(0);
//	duyệt hết các hàng
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//	lựa chọn sheet
                Row row = sheet.getRow(i);

//	trỏ đến từng cột trong từng hàng. Chú ý kiểu data trong file excel
                int a = (int) row.getCell(0).getNumericCellValue();
//                String name = row.getCell(1).getStringCellValue();
//                String checkpass = row.getCell(2).getStringCellValue();
                int b = (int) row.getCell(1).getNumericCellValue();

                System.out.println("Data row " + i +": " + a + " " + b);
//                Account account = new Account();
//                account.setName(name);
//                account.setPass(checkpass);
//                account.setSTT(STT);
//                inserData(account, request, response);

                wb.close();

            }   // ghi file
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            
        }
    }
        // ghi file
    public static void writeExcel(List<DoanhThu> doanhThu) throws IOException {
        FileOutputStream file = null;
        HSSFWorkbook workbook = null;
        try {
            file = new FileOutputStream("D://hine/test.xls");
//            file = new FileOutputStream("D://hine/"+java.time.LocalTime.now()+".xls");
             workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("name");
            
            int rowIndex =2;
//            format header
             writeHeader(sheet, rowIndex);
                rowIndex++;
        for (DoanhThu dThu : doanhThu) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeDoanhThu(dThu, row);
            rowIndex++;
        }
        
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(2).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);            
            workbook.write(file);
            workbook.close();
            file.close();
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } finally {
        }
        
        
    }    
    
    
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("STT");
 
        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã sản phẩm");
 
        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên sản phẩm");
 
        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số lượng");
 
          cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Trạng thái");
        
        
        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giá nhập");
        
         cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Gia bán");
        
         cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Lợi nhuận");
        
         cell = row.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chú thích");
    }
    
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
     // Write data
    private static void writeDoanhThu(DoanhThu doanhThu, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
             
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
         
        Cell cell = row.createCell(0);
        cell.setCellValue(doanhThu.getSTT());
 
        cell = row.createCell(1);
        cell.setCellValue(doanhThu.getMaSP());
 
        cell = row.createCell(2);
        cell.setCellValue(doanhThu.getTenSP());
        cell.setCellStyle(cellStyleFormatNumber);
 
        cell = row.createCell(3);
        cell.setCellValue(doanhThu.getSoLuong());
        
        cell = row.createCell(4);
        cell.setCellValue(doanhThu.getTrangThai());
        
        cell = row.createCell(5);
        cell.setCellValue(doanhThu.getGiaNhap());
        
        cell = row.createCell(6);
        cell.setCellValue(doanhThu.getGiaBan());
        
        cell = row.createCell(7);
        cell.setCellValue(doanhThu.getLoiNhuan());
        
        cell = row.createCell(8);
        cell.setCellValue(doanhThu.getChuThich());
         
    }
    
      // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
    
    
}
