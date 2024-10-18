package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FromHashMapToExcel {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("Asisipho", "Ntloko");
        map.put("Aphelele", "Dladla");
        map.put("Wandile", "Mlambo");
        map.put("Ezile", "Ncwabane");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("My clan");

        int rows = 0;

        for (Map.Entry data: map.entrySet()) {

            XSSFRow row = sheet.createRow(rows++);
            row.createCell(0).setCellValue((String)data.getKey());
            row.createCell(1).setCellValue((String)data.getValue());
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("lambos.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
