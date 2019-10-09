package me.smash;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePoiMain {

  public static void main(String[] args) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet1 = workbook.createSheet("fist");
    Sheet sheet2 = workbook.createSheet("second");

    Row row10 = sheet1.createRow(0);
    Row row11 = sheet1.createRow(1);
    Cell cell100 = row10.createCell(0);
    Cell cell101 = row10.createCell(1);
    cell100.setCellValue("Name");
    cell101.setCellValue("Age");
    Cell cell110 = row11.createCell(0);
    Cell cell111 = row11.createCell(1);
    cell110.setCellValue("Mohammad");
    cell111.setCellValue(34);


    try (FileOutputStream fos = new FileOutputStream("workbook.xlsx")) {
      workbook.write(fos);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
