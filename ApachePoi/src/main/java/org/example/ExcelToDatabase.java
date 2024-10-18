package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelToDatabase {

    public static void main(String args[]) {


        FileInputStream inputStream = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "123ase");
            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE CITIES (, , , )";
            statement.execute(sql);

            inputStream = new FileInputStream("country.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Location data");



            int rows = sheet.getLastRowNum();

            for (int r = 0; r < rows; r++) {

                XSSFRow row = sheet.getRow(240);

                String code = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String continent = row.getCell(2).getStringCellValue();
                String region = row.getCell(3).getStringCellValue();
                Double surfaceArea = row.getCell(4).getNumericCellValue();
                String indepYear = row.getCell(5).getStringCellValue();

                String sqlInsert = "INSERT INTO `world`.`country` (`Code`, `Name`, `Continent`, `Region`, `SurfaceArea`, `IndepYear`) VALUES ('"+code+"', '"+name+"', '"+continent+"', '"+region+"', '"+surfaceArea+"', '"+indepYear+"')";
                statement.execute(sqlInsert);
                statement.execute("commit");
            }
            workbook.close();
            inputStream.close();
            connection.close();

            System.out.println("Done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
