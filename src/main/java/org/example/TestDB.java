package org.example;

public class TestDB {

    public static void main(String[] args) {

        //Print Table data
        FetchTableData tableData = new FetchTableData();
        tableData.printTableValues();

        //Testing export data from database to excel
            ExportToExcel export = new ExportToExcel();
            export.exportToExcel();

        //Testing inserting data from excel to database
//        ExcelToDatabase excelToDB = new ExcelToDatabase();
//        excelToDB.importToDatabase();

        }
    }
