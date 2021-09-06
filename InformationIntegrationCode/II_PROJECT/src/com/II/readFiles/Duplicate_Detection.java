package com.II.readFiles;

import java.sql.*;
 
public class Duplicate_Detection 
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
            
            String sql = "DELETE FROM MERGER WHERE rowid not in (SELECT MIN(rowid) FROM MERGER GROUP BY ARTICLE_ID, TITLE)";
            statement.executeQuery(sql);
            
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
