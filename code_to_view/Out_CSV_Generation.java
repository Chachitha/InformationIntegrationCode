package com.II.readFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Out_CSV_Generation 
{

public static void main(String[] args) throws Exception
{

				int startYear=0;
				int endYear=0;

			  Class.forName("oracle.jdbc.driver.OracleDriver");
		      Connection connect = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:orcl","II_project","II_project");
		      Statement statement = connect.createStatement();
              //user query where he/she can specify constrains with values for startYear and endYear

		      String sql = "";
              if(startYear==0 && endYear==0){
            	  sql = "SELECT * from MERGER";
              }else if(startYear!=0 && endYear!=0){
            	  sql = "SELECT * from MERGER where YEAR>="+startYear+"and YEAR <="+endYear;
              }else if(startYear!=0 && endYear==0){
            	  sql = "SELECT * from MERGER where YEAR>="+startYear;  
              }else if(startYear==0 && endYear!=0){
            	  sql = "SELECT * from MERGER where YEAR<="+endYear;  
              }

		      ResultSet resultSet = statement.executeQuery(sql);
              int count=0;

		      XSSFWorkbook workbook = new XSSFWorkbook(); 
		      XSSFSheet spreadsheet = workbook.createSheet("Merger Table");
		      
		      XSSFRow row = spreadsheet.createRow(0);
		      XSSFCell cell;
		      //Create column names
		      cell = row.createCell(0);
		      cell.setCellValue("ARTICLE_ID");
		      cell = row.createCell(1);
		      cell.setCellValue("AUTHOR");
		      cell = row.createCell(2);
		      cell.setCellValue("TITLE");
		      cell = row.createCell(3);
		      cell.setCellValue("PUBLISHER");
		      cell = row.createCell(4);
		      cell.setCellValue("YEAR");
		      cell = row.createCell(5);
		      cell.setCellValue("VOLUME");
		      cell = row.createCell(6);
		      cell.setCellValue("PAGES");
		      cell = row.createCell(7);
		      cell.setCellValue("ISBN");
		      cell = row.createCell(8);
		      cell.setCellValue("BIBURL");
		      cell = row.createCell(9);
		      cell.setCellValue("SOURCE");
		      int i = 0;

		      while(resultSet.next()) {
            	  ++count;

            	  // Retrieve values from table and assign values to columns
		         row = spreadsheet.createRow(i);
		         cell = row.createCell(0);
		         cell.setCellValue(resultSet.getString("ARTICLE_ID"));
		         cell = row.createCell(1);
		         cell.setCellValue(resultSet.getString("AUTHOR"));
		         cell = row.createCell(2);
		         cell.setCellValue(resultSet.getString("TITLE"));
		         cell = row.createCell(3);
		         cell.setCellValue(resultSet.getString("PUBLISHER"));
		         cell = row.createCell(4);
		         cell.setCellValue(resultSet.getInt("YEAR"));
		         cell = row.createCell(5);
		         cell.setCellValue(resultSet.getString("VOLUME"));
		         cell = row.createCell(6);
		         cell.setCellValue(resultSet.getString("PAGES"));
		         cell = row.createCell(7);
		         cell.setCellValue(resultSet.getString("ISBN"));
		         cell = row.createCell(8);
		         cell.setCellValue(resultSet.getString("BIBURL"));
		         cell = row.createCell(9);
		         cell.setCellValue(resultSet.getString("SOURCE"));
		         i++;
		      }
              System.out.println(count);
              // displays number of rows 

		      FileOutputStream out = new FileOutputStream(new File("MergerTable.xlsx"));
		      workbook.write(out);
		      out.close();
		      workbook.close();
		      System.out.println("MergerTable.xlsx written successfully");
		   }
		}
	


