package com.II.readFiles;

import java.sql.*;
 
public class CreateTable 
{
 
    public static void main(String[] args) 
    {
        
 
        Connection connection = null;
        Statement statement = null;
        try 
        {
    		try 
    		{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
  		  
    		try
    		{
    			//step2 create  the connection object  
    		connection=DriverManager.getConnection(  
    		"jdbc:oracle:thin:@localhost:1521:orcl","II_project","II_project");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            // Create 3 tables
            String sql="CREATE TABLE CSV_IEEE(DOC_TITLE VARCHAR2(500),  Authors VARCHAR2(500),  Author_Affiliations VARCHAR2(2048),  Publication_Title VARCHAR2(1024),  Date_Added_To_Xplore VARCHAR2(500),  Publication_Year NUMBER(32),  Volume NUMBER(32),  Issue NUMBER(32),  Start_Page NUMBER(32),  End_Page NUMBER(32),  Abstract VARCHAR2(1815),  ISSN VARCHAR2(500),  ISBNs VARCHAR2(500),  DOI VARCHAR2(500),  Funding_Information VARCHAR2(1024),  PDF_Link VARCHAR2(500),  Author_Keywords VARCHAR2(500),  IEEE_Terms VARCHAR2(500),  INSPEC_Controlled_Terms VARCHAR2(1024),  INSPEC_Terms VARCHAR2(1024),  Mesh_Terms VARCHAR2(500),  Article_Citation_Count NUMBER(32),  Reference_Count VARCHAR2(500),  License VARCHAR2(400),  Online_Date VARCHAR2(500),  Issue_Date VARCHAR2(500),  Meeting_Date VARCHAR2(500),  Publisher VARCHAR2(500),  Document_Identifier VARCHAR2(500),SOURCE VARCHAR2(500),ARTICLE_ID VARCHAR2(500))";
            statement.execute(sql);
            
            String sql1="CREATE TABLE BIBTEX_DBLP(ARTICLE_ID VARCHAR2(500 ), AUTHOR VARCHAR2(1500 ), EDITOR VARCHAR2(1500 ), TITLE VARCHAR2(1500 ), SCHOOL VARCHAR2(1500 ), BOOKTITLE VARCHAR2(1500 ), SERIES VARCHAR2(1500 ), JOURNAL VARCHAR2(1500 ), VOLUME VARCHAR2(500 ), NUMBER_T VARCHAR2(200 ), PAGES VARCHAR2(500 ), PUBLISHER VARCHAR2(1500 ), YEAR NUMBER, NOTE VARCHAR2(1500 ), URL VARCHAR2(1500 ), DOI VARCHAR2(1500 ), ARCHIVEPREFIX VARCHAR2(1500 ), EPRINT VARCHAR2(1500 ), ISBN VARCHAR2(1500 ), TIMESTAMP VARCHAR2(1500 ), BIBURL VARCHAR2(1500 ), BIBSOURCE VARCHAR2(1500 ), SOURCE VARCHAR2(1500 ))";
            statement.execute(sql1);

            String sql2="CREATE TABLE RIS_SCIENCE_DIRECT(TY VARCHAR2(500 ),T1 VARCHAR2(500 ),AU VARCHAR2(500 ),BT VARCHAR2(500 ),PB VARCHAR2(500 ),CY VARCHAR2(500 ),JO VARCHAR2(500 ),VL VARCHAR2(50 ),IS_R NUMBER(4,0),SP NUMBER(20,0),EP NUMBER(20,0),PY NUMBER(20,0),DA VARCHAR2(400 ),T2 VARCHAR2(500 ),SN VARCHAR2(500 ),DO VARCHAR2(500 ),UR VARCHAR2(500 ),KW VARCHAR2(1000 ),AB CLOB,ER VARCHAR2(20 ),SOURCE VARCHAR2(20 ),ARTICLE_ID VARCHAR2(50 ))";
            statement.execute(sql2);

            
            connection.commit();
            connection.close();
  
         
    		 } catch (SQLException ex) {
    	            ex.printStackTrace();
    	 
    	            try {
    	                connection.rollback();
    	            } catch (SQLException e) {
    	                e.printStackTrace();
    	            }
    	        }
    	 
    	    }
        catch (Exception e){
        	e.printStackTrace();
        }
    	}
}
