package org.example;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadFromExcelToHashMap {

    public static void main(String[] args) {

        String path = "students.xlsx";

        try {
            FileInputStream inputStream = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = workbook.getSheet("Student data");
            int rows = sheet.getLastRowNum();

            Map<String, String> map = new HashMap<>();

            for (int i = 0; i < rows; i++) {
                String key = sheet.getRow(i).getCell(0).getStringCellValue();
                String value = sheet.getRow(i).getCell(1).getStringCellValue();
                map.put(key, value);
            }

            for (Map.Entry entry: map.entrySet()) {
                System.out.println(entry.getKey()+" "+entry.getValue());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
