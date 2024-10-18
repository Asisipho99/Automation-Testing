package org.example;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FormattingCellColor {

    public static void main(String args[]) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Sheet1");

        XSSFRow row = sheet.createRow(1);

        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.BIG_SPOTS);

        XSSFCell cell = row.createCell(1);
        cell.setCellValue("Welcome");
        cell.setCellStyle(style);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("styles.xlsx");
            workbook.write(outputStream);
            outputStream.close();

            System.out.println("Done!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
