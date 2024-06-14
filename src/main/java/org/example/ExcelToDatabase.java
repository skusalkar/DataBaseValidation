package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToDatabase extends DBConnection{

    public void importToDatabase() {

        //excel file name having data
        String excelFilePath = "inputData.xlsx";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;

            for (Row row : sheet) {
                // Skip header row
                if (rowCount == 0) {
                    rowCount++;
                    continue;
                }

                ///column names
                int EmpID = 0;
                String EmpName = "";
                int EmpAge=0;
                String EmpDept="";

                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            EmpID = (int) cell.getNumericCellValue();
                            break;
                        case 1:
                            EmpName = cell.getStringCellValue();
                            break;
                        case 2:
                            EmpAge = (int) cell.getNumericCellValue();
                            break;
                        case 3:
                            EmpDept = cell.getStringCellValue();
                            break;
                        // Add more cases for additional columns
                    }
                }

                // Insert data into database
                String sqlQuery = "INSERT INTO testdb.employee (EmpID, EmpName,EmpAge,EmpDept) VALUES (?, ?,?,?)";
                try (PreparedStatement statement = connection.prepareStatement(sqlQuery))
                {
                    statement.setInt(1, EmpID);
                    statement.setString(2, EmpName);
                    statement.setInt(3, EmpAge);
                    statement.setString(4, EmpDept);
                    statement.executeUpdate();
                }

                rowCount++;
            }

            System.out.println("Data inserted into database successfully.");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
