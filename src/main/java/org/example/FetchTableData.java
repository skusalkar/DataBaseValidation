package org.example;

import java.sql.*;

public class FetchTableData extends  DBConnection {

    private String selectQuery = "SELECT * FROM testdb.employee";

    public void printTableValues()
    {

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            // Iterate through the result set and print values
            while (rs.next()) {
                // Assuming your_table_name has columns "EmpID", "EmpName", etc.
                String column1Value = rs.getString("EmpID");
                String column2Value = rs.getString("EmpName");

                // Print the retrieved values
                System.out.println("EmpID: " + column1Value + ", EmpName: " + column2Value);

            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data from database: " + e.getMessage());
        }

    }
}
