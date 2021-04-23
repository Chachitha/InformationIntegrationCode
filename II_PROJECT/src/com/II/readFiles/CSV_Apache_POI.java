package com.II.readFiles;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CSV_Apache_POI {

	public static void main(String[] args) {
		int count = 0 ;
		Connection connection = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String[] headerOrg = { "DOC_TITLE", "AUTHORS", "AUTHOR_AFFILIATIONS",
				"PUBLICATION_TITLE", "DATE_ADDED_TO_XPLORE",
				"PUBLICATION_YEAR", "VOLUME", "ISSUE", "START_PAGE",
				"END_PAGE", "ABSTRACT", "ISSN", "ISBNS", "DOI",
				"FUNDING_INFORMATION", "PDF_LINK", "AUTHOR_KEYWORDS",
				"IEEE_TERMS", "INSPEC_CONTROLLED_TERMS", "INSPEC_TERMS",
				"MESH_TERMS", "ARTICLE_CITATION_COUNT", "REFERENCE_COUNT",
				"LICENSE", "ONLINE_DATE", "ISSUE_DATE", "MEETING_DATE",
				"PUBLISHER", "DOCUMENT_IDENTIFIER"};
		// import statements
		{
			try {
				FileInputStream file = new FileInputStream(
						new File(
								"D:\\PG_MS\\Sem1\\Information_Integration\\Project\\Data_Source\\IEEE_Data_XL.xlsx"));
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				boolean check = false;
				// Get first/desired sheet from the workbook
				XSSFSheet sheet = workbook.getSheetAt(0);
				for (Row row : sheet) {
					if (!check) {
						check = true;
						continue;
					}
					ArrayList<String> headerList = new ArrayList<String>();
					ArrayList<String> value = new ArrayList<String>();
					for (int cn = 0; cn < row.getLastCellNum(); cn++) {
						// If the cell is missing from the file, generate a
						// blank one
						// (Works by specifying a MissingCellPolicy)
						Cell cell = row.getCell(cn,
								Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						if (!(cell.toString().trim().equals(""))) {
							headerList.add(headerOrg[cn]);
							value.add(cell.toString());
						}
						// System.out.println(headerList);
						// for(int i=1 ; i<=headerOrg.length ; i++)
						// {
						// if(headerList.get(i)==headerOrg[i])
						// {
						// value.add(cell.toString());
						// }
						// }

						// Print the cell for debugging

					//	System.out.println("CELL: " + cn + " --> "
						//		+ cell.toString());
					}
					Set<String> set = new LinkedHashSet<String>(); 
					set.addAll(headerList);//using set to remove duplicate headers
					headerList.clear();// clear all the previous values from headerList
					headerList.addAll(set);// add fresh values to headerList without duplicates
					String temp=value.get(1).replaceAll("; "," and ");//Replace all values in a cell separated by comma with 'and'
					value.remove(1);
					value.add(1,temp);
					headerList.add("SOURCE");
					value.add("CSV");
					headerList.add("ARTICLE_ID");
					// Extracting multiple authors name to generate key such that:
					//First author's last name+other authors' first letters of their last names+year of publication

					String[] keyArray=value.get(1).trim().split(" and ");
		            String key="";
		            if(value.get(1).length()>0){
		            	for(String keyEach:keyArray){
			            	if(key.trim().length()==0){
			            		String[] tempArr=keyEach.split(" ");
			            		key=key+tempArr[tempArr.length-1].trim();
			            	}else{
			            		String[] tempArr=keyEach.split(" ");
			            		key=key+tempArr[tempArr.length-1].trim().charAt(0);
			            	}
			            }
			            key=(key+value.get(4).replace(".0", "")).replaceAll("\\\\", "").replaceAll("'", "").replaceAll("-", "").replaceAll("~", "").replaceAll("\"", "");
			            // Replace all special characters in the cell
		            }
		            value.add(key);
					ArrayList<String> list = new ArrayList<String>(
							Collections.nCopies(headerList.size(), "?"));
					System.out.println(headerList);
					System.out.println(value);
					connection = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:orcl",
							"II_project", "II_project");
					//Establish connection
					connection.setAutoCommit(false);
					//generate insert query
					String query = "Insert into CSV_IEEE ";
					query = query
							+ headerList.toString().replace("[", "(")
									.replace("]", ")");
					query = query
							+ " values "
							+ list.toString().replace("[", "(")
									.replace("]", ")");
					System.out.println(query);
					PreparedStatement stmt = connection.prepareStatement(query);
					for (int i = 0; i < value.size(); i++) {
						stmt.setString(i + 1, value.get(i));
					}
					stmt.execute();
					connection.commit();
					connection.close();
					++count;
					// if (count == 3) {
					// break;
					// }
				}
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

}
