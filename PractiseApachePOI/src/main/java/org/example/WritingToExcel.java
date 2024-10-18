package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingToExcel {

    public static void main(String[] args) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employees");

        Object empData[][] = {
                {"EmpID", "Name", "Job"},
                {101, "David", "Engineer"},
                {102, "Smith", "Manager"},
                {103, "Scoot", "Analyst"}
        };

        int rows = empData.length;
        int cols = empData[0].length;

        for (int i = 0; i < rows; i++) {

            XSSFRow row = sheet.createRow(i);

            for (int j = 0; j < cols; j++) {

                XSSFCell cell = row.createCell(j);

                Object value = empData[i][j];

                if (value instanceof String)
                    cell.setCellValue((String) value);

                if (value instanceof Boolean)
                    cell.setCellValue((Boolean) value);

                if (value instanceof Integer)
                    cell.setCellValue((Integer) value);
                System.out.println(value);
            }

        /**int rowCount =0;

        for (Object employees[]: empData) {
            XSSFRow row = sheet.createRow(rowCount++);

            int colCount = 0;
            for (Object value: employees) {

                XSSFCell cell = row.createCell(colCount++);

                if (value instanceof String)
                    cell.setCellValue((String) value);

                if (value instanceof Boolean)
                    cell.setCellValue((Boolean) value);

                if (value instanceof Integer)
                    cell.setCellValue((Integer) value);
                System.out.println(value);
            }**/

            try{
                FileOutputStream outputStream = new FileOutputStream("Employess.xlsx");
                workbook.write(outputStream);
                outputStream.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
