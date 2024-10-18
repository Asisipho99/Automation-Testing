package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

// Workbook --> Sheet --> Cells
public class WritingExcelDemo1 {
    public static void main(String args[]) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Emp Info");

        Object empdata[][] = {
                {"EmpID", "Name", "Job"},
                {101, "David", "Engineer"},
                {102, "Smith", "Manager"},
                {103, "Scoot", "Analyst"}
        };

        /**int rows = empdata.length;
        int cols = empdata[0].length;

        System.out.println(rows);
        System.out.println(cols);

        for (int r=0; r<rows; r++) {

            XSSFRow row = sheet.createRow(r);

            for (int c=0; c<cols; c++) {

                XSSFCell cell = row.createCell(c);
                Object value = empdata[r][c];

                if (value instanceof String) {
                    cell.setCellValue((String)value);
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer)value);
                }
                if (value instanceof Boolean) {
                    cell.setCellValue((Boolean)value);
                }
            }**/

        int rowCount = 0;
        for(Object emp[]:empdata) {
            XSSFRow row = sheet.createRow(rowCount);
            int columnCount = 0;

            for (Object value:emp) {

                XSSFCell cell = row.createCell(columnCount++);

                if (value instanceof String) {
                    cell.setCellValue((String)value);
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer)value);
                }
                if (value instanceof Boolean) {
                    cell.setCellValue((Boolean)value);
                }
            }

            String filePath = "employees.xlsx";
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(filePath);
                workbook.write(outputStream);

                outputStream.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            System.out.println("Employee.xls file written successfully..");

            }
    }
}
