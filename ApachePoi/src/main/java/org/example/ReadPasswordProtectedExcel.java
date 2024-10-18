package org.example;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadPasswordProtectedExcel {

    public static void main(String args[]) {

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("customers.xlsx");
            String password = "test123";

            //XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream, password);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rows = sheet.getLastRowNum();
            System.out.println(rows);

            int cols = sheet.getRow(0).getLastCellNum();
            System.out.println(cols);


            for (int i = 0; i < rows; i++) {

                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {

                    XSSFCell cell = row.getCell(j);

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
                    System.out.println("|");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
