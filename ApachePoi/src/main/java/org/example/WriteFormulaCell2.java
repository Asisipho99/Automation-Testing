package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFormulaCell2 {

    public static void main(String args[]) {

        String path = "calc.xlsx";

        try {
            FileInputStream inputStream = new FileInputStream(path);

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            sheet.getRow(0).getCell(1).setCellFormula("SUM(A2:C6");

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);

            workbook.close();
            outputStream.close();

            System.out.println("Done!!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
