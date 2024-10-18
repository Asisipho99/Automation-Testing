package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashMapToExcel {

    public static void main(String args[]) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Student data");

        Map<String, String> data = new HashMap<>();
        data.put("101", "John");
        data.put("102", "Smith");
        data.put("103", "Scott");
        data.put("104", "Kim");
        data.put("105", "Mary");

        int rowNo = 0;

        for (Map.Entry entry:data.entrySet()) {

            XSSFRow row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue((String)entry.getValue());
            row.createCell(1).setCellValue((String)entry.getValue());
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("students.xlsx");
            workbook.write(outputStream);
            outputStream.close();
            System.out.println("Excel written successfully");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
