package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.*;

public class ExportToExcel extends  DBConnection {
    private String selectQuery = "SELECT * FROM testdb.employee";

    public void exportToExcel (){

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            // Create a new workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            // Iterate through the result set and write data to Excel
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);

                // Assuming your_table_name has columns "column1", "column2", etc.
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(rs.getString("EmpID"));

                Cell cell2 = row.createCell(1);
                cell2.setCellValue(rs.getString("EmpName"));

                // Add cells for other columns similarly
            }

            // Write the workbook to a file
            try (FileOutputStream outputStream = new FileOutputStream("Data.xlsx")) {
                workbook.write(outputStream);
                System.out.println("Data exported to Excel successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data from database: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error exporting data to Excel: " + e.getMessage());
        }
    }

    }

