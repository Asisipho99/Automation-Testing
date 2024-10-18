package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class DatabaseToExcel {

    public static void main(String args[]) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "123ase");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM country");

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Locations Data");

            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("Code");
            row.createCell(1).setCellValue("Name");
            row.createCell(2).setCellValue("Continent");
            row.createCell(3).setCellValue("Region");
            row.createCell(4).setCellValue("SurfaceArea");
            row.createCell(5).setCellValue("IndepYear");

            int r=1;
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String continent = resultSet.getString("continent");
                String region = resultSet.getString("region");
                Double surfaceArea = resultSet.getDouble("surfacearea");
                int indepYear = resultSet.getInt("indepyear");

                row = sheet.createRow(r++);

                row.createCell(0).setCellValue(code);
                row.createCell(1).setCellValue(name);
                row.createCell(2).setCellValue(continent);
                row.createCell(3).setCellValue(region);
                row.createCell(4).setCellValue(surfaceArea);
                row.createCell(5).setCellValue(indepYear);

                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream("country.xlsx");
                    workbook.write(outputStream);
                    outputStream.close();

                    System.out.println("Done!");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
