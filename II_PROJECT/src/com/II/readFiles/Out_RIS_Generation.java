package com.II.readFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Out_RIS_Generation 
{

	public static void main(String[] args) 
	{
		int startYear=0;
		int endYear=0;
		
		try 
		{
            FileWriter writer = new FileWriter("RIS_Generation.ris");

               // JDBC driver name and database URL
              
               //  Database credentials
               
               Connection conn = null;
               java.sql.Statement stmt = null;
               try{
                  //STEP 2: Register JDBC driver
                  Class.forName("oracle.jdbc.driver.OracleDriver");

                  //STEP 3: Open a connection
                  conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","II_project","II_project");
                  
                  //STEP 4: Execute a query
                  stmt = conn.createStatement();

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
                  ResultSet rs = stmt.executeQuery(sql);
                  int count=0;
                  //STEP 5: Extract data from result set
                  while(rs.next()){
                	  ++count;
                     //Retrieve by column name
                     String Author = rs.getString("AUTHOR");
                     String Title = rs.getString("TITLE");
                     String Publisher = rs.getString("PUBLISHER");
                     String Year  = rs.getString("YEAR");
                     String Volume = rs.getString("VOLUME");
                     String Pages = rs.getString("PAGES");
                     String isbn = rs.getString("ISBN");
                     String Biburl = rs.getString("BIBURL");

                     //Display values in RIS format
 						if (Title!=null)
						{
		                     writer.write("\nTI \t - " + Title);
						}
						if (Author!=null)
						{
							String[] temp=Author.split(" and ");
							for (String string : temp) {
			                     writer.write("\nAU \t - " + string);
							}
						}
						if (Publisher!=null)
						{
		                     writer.write("\nPU \t - " + Publisher);
						}
						if (Year!=null)
						{
		                     writer.write("\nPY \t - " + Year);
						}
						if (Volume!=null)
						{
		                     writer.write("\nVL \t - " + Volume);
						}
						if (Pages!=null)
						{
							if(Pages.contains("--"))
							{ 
								String[] tempArr = Pages.split("--");

						if(tempArr.length>0)
						{
			                    writer.write("\nSP \t - " + tempArr[0]);
	   	                        if(tempArr.length>1)
			                    {
	   	                        	writer.write("\nEP \t - " + tempArr[1]);
			                    }
		                     }
							else
							{
			                  writer.write("\nSP \t - " + Pages);
							}
						}
						}
						if (isbn!=null)
						{
		                     writer.write("\nSN \t - " + isbn);
						}
						if (Biburl!=null)
						{
		                     writer.write("\nUR \t - " + Biburl);
						}
					
                    writer.write("\n");
                    writer.write("\n");
                  }
                  System.out.println(count);
                  //Display number of rows
                  rs.close();
               }catch(SQLException se){
                  //Handle errors for JDBC
                  se.printStackTrace();
               }catch(Exception e){
                  //Handle errors for Class.forName
                  e.printStackTrace();
               }finally{
                  //finally block used to close resources
                  try{
                     if(stmt!=null)
                        conn.close();
                  }catch(SQLException se){
                  }// do nothing
                  try{
                     if(conn!=null)
                        conn.close();
                  }catch(SQLException se){
                     se.printStackTrace();
                  }//end finally try
               }//end try
            
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
