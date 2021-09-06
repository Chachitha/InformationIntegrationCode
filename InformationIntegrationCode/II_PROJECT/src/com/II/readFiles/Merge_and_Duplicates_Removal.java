package com.II.readFiles;

import java.sql.*;
 
public class Merge_and_Duplicates_Removal 
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
            
            //Merge 3 tables with necessary formatting options
            String sql = "CREATE TABLE MERGER AS ((select BIBTEX_DBLP.ARTICLE_ID,BIBTEX_DBLP.AUTHOR,BIBTEX_DBLP.TITLE,BIBTEX_DBLP.PUBLISHER,BIBTEX_DBLP.YEAR, BIBTEX_DBLP.VOLUME,BIBTEX_DBLP.PAGES,BIBTEX_DBLP.ISBN,BIBTEX_DBLP.BIBURL,BIBTEX_DBLP.SOURCE from BIBTEX_DBLP) UNION (select RIS_SCIENCE_DIRECT.ARTICLE_ID,RIS_SCIENCE_DIRECT.AU,RIS_SCIENCE_DIRECT.T1,RIS_SCIENCE_DIRECT.JO,RIS_SCIENCE_DIRECT.PY, RIS_SCIENCE_DIRECT.VL, CONCAT(CONCAT(CAST(RIS_SCIENCE_DIRECT.SP as VARCHAR2(30)),'--'),CAST(RIS_SCIENCE_DIRECT.EP as VARCHAR2(30))),RIS_SCIENCE_DIRECT.SN,RIS_SCIENCE_DIRECT.DO,RIS_SCIENCE_DIRECT.SOURCE from RIS_SCIENCE_DIRECT) UNION (select CSV_IEEE.ARTICLE_ID,CSV_IEEE.AUTHORS,CSV_IEEE.DOC_TITLE,CSV_IEEE.PUBLISHER,CSV_IEEE.PUBLICATION_YEAR, CAST(CSV_IEEE.VOLUME as VARCHAR2(30)), CONCAT(CONCAT(CAST(CSV_IEEE.START_PAGE as VARCHAR2(30)),'--'), CAST(CSV_IEEE.END_PAGE as VARCHAR2(30))), CSV_IEEE.ISBNS,CSV_IEEE.DOI,CSV_IEEE.SOURCE from CSV_IEEE))";
            statement.execute(sql);

            //Identify duplicates and delete corresponding rows from our final integrated table "MERGER"
            String sql1 = "DELETE FROM MERGER WHERE rowid not in (SELECT MIN(rowid) FROM MERGER GROUP BY ARTICLE_ID, TITLE)";
            statement.executeQuery(sql1);

            
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
