package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {

        String excelFilePath = "countries.xlsx";
        FileInputStream inputStream = null;
        try {

            inputStream = new FileInputStream(excelFilePath);

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            if (inputStream == null) {
                throw new FileNotFoundException("File not found in the classpath: " + excelFilePath);
            }

            //XSSFSheet  sheet = workbook.getSheet("Sheet1");
            XSSFSheet sheet = workbook.getSheetAt(0);

//            int rows = sheet.getLastRowNum();
//            int cols = sheet.getRow(1).getLastCellNum();
//
//            for (int r = 0; r <= rows; r++) {
//
//                XSSFRow row = sheet.getRow(r);
//                for (int c = 0; c < cols; c++) {
//
//                    XSSFCell cell = row.getCell(c);
//
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            System.out.print(cell.getStringCellValue()+"\t");
//                            break;
//                        case NUMERIC:
//                            System.out.print(cell.getNumericCellValue()+"\t");
//                            break;
//                        case BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue()+"\t");
//                            break;
//                    }
//                }
//                System.out.println();
//            }

            Iterator iterator = sheet.iterator();

            while (iterator.hasNext()) {

                XSSFRow row = (XSSFRow) iterator.next();
                Iterator cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    XSSFCell cell = (XSSFCell) cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue()+"\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue()+"\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue()+"\t");
                            break;
                    }
                    System.out.println(" | ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}