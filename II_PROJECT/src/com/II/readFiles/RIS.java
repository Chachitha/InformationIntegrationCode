package com.II.readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.sql.*;

public class RIS {

	public RIS() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException 
	{
		Connection connection = null;
        String TY = "";
		String T1 = "";
		String AU = "";
		String BT = "";
		String PB = "";
		String CY = "";
		String JO = "";
		String VL = "";
		String IS_R = "";
		String SP = "";
		String EP = "";
		String PY = "";
		String DA = "";
		String T2 = "";
		String SN = "";
		String DO = "";
		String UR = "";
		String KW = "";
		String AB = "";
		String ER = "";

		File f = new File("D:/PG_MS/Sem1/Information_Integration/Project/Data_Source/ScienceDirect_Data.ris");																						// stub
		try 
		{
			try 
    		{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} 
			try
			{
			connection=DriverManager.getConnection(  
		    		"jdbc:oracle:thin:@localhost:1521:orcl","II_project","II_project");
		            connection.setAutoCommit(false);
			BufferedReader br = new BufferedReader(new FileReader(f));
			ArrayList<String> headerList = new ArrayList<String>();
			String line = br.readLine();
			String header = "";
			String value = "";
			while (line != null) 
			{
				line=line.replace("IS  - ", "IS_R  - ");//IS- predefined variable in sql. So we replace with IS_R
				line=line.replace("A2  - ", "AU  - ");//case of semantic Heterogeneity

				if (line.trim().equals("")) 
				{
//					if(exit==5){
//						break;
//					}
					int count=1;
			        Set<String> set = new LinkedHashSet<String>();
			        set.addAll(headerList);
			        headerList.clear();
			        headerList.addAll(set);
			        headerList.add("SOURCE");
					headerList.add("ARTICLE_ID");
			        ArrayList<String> list = new ArrayList<String>(Collections.nCopies(headerList.size(), "?"));
					// Generate insert query
			        String query="Insert into RIS_SCIENCE_DIRECT ";
					query=query+headerList.toString().replace("[", "(").replace("]", ")");
					query =query+" values "+list.toString().replace("[", "(").replace("]", ")");
		            System.out.println(query);
					PreparedStatement stmt = connection.prepareStatement(query);
					// Assign values to columns only when the column exists
					if (!TY.equals(""))
					{	
		                stmt.setString(count, TY);
		                ++count;
					}
					if (!T1.equals(""))
					{
		                stmt.setString(count, T1);
		                ++count;

					}
					if (!AU.equals(""))
					{
						AU=AU.trim().replaceAll(" and$", "");
		                stmt.setString(count, AU);
		                ++count;

					}
					if (!BT.equals(""))
					{
		                stmt.setString(count, BT);
		                ++count;

					}
					if (!PB.equals(""))
					{
		                stmt.setString(count, PB);
		                ++count;

					}
					if (!CY.equals(""))
					{
		                stmt.setString(count, CY);
		                ++count;

					}
					if (!JO.equals(""))
					{
		                stmt.setString(count, JO);
		                ++count;

					}
					if (!VL.equals(""))
					{
		                stmt.setString(count, VL);
		                ++count;

					}
					if (!IS_R.equals(""))
					{
		                stmt.setString(count, IS_R);
		                ++count;

					}
					if (!SP.equals(""))
					{
		                stmt.setString(count, SP);
		                ++count;
		                
					}
					if (!EP.equals(""))
					{
		                stmt.setString(count, EP);
		                ++count;

					}
					if (!PY.equals(""))
					{
		                stmt.setString(count, PY);
		                ++count;

					}
					if (!DA.equals(""))
					{

		                stmt.setString(count, DA);
		                ++count;

					}
					if (!T2.equals(""))
					{

		                stmt.setString(count, T2);
		                ++count;

					}
					if (!SN.equals(""))
					{

		                stmt.setString(count, SN);
		                ++count;

					}
					if (!DO.equals(""))
					{

		                stmt.setString(count, DO);
		                ++count;

					}
					if (!UR.equals(""))
					{

		                stmt.setString(count, UR);
		                ++count;

					}
					if (!KW.equals(""))
					{

		                stmt.setString(count, KW);
		                ++count;

					}
					if (!AB.equals(""))
					{

		                stmt.setString(count, AB);
		                ++count;

					}
					if (!ER.equals(""))
					{

		                stmt.setString(count, ER);
		                ++count;

					}
					stmt.setString(count, "RIS");
					// Extracting multiple authors name to generate key such that: First author's last name+other authors' first letters of their last names+year of publication
					String[] keyArray=AU.trim().split(" and ");
		            String key="";
		            if(AU.length()>0){
		            	for(String keyEach:keyArray){
			            	if(key.trim().length()==0){
			            		String[] tempArr=keyEach.split(" ");
			            		key=key+tempArr[tempArr.length-1].trim();
			            	}else{
			            		String[] tempArr=keyEach.split(" ");
			            		key=key+tempArr[tempArr.length-1].trim().charAt(0);
			            	}
			            }
			            key=(key+PY).replaceAll("\\\\", "").replaceAll("'", "").replaceAll("-", "").replaceAll("~", "").replaceAll("\"", "");	
		            }
		            stmt.setString(++count, key);
					stmt.execute();
//					System.out.println(TY);
//					System.out.println(T1);
//					System.out.println(AU);
//					System.out.println(JO);
//					System.out.println(VL);
//					System.out.println(SP);
//					System.out.println(EP);
//					System.out.println(PY);
//					System.out.println(DA);
//					System.out.println(T2);
//					System.out.println(SN);
//					System.out.println(DO);
//					System.out.println(UR);
//					System.out.println(KW);
//					System.out.println(AB);
//					System.out.println(ER);
					headerList = new ArrayList<String>();
					TY = "";
					T1 = "";
					AU = "";
					BT = "";
					PB = "";
					CY = "";
					JO = "";
					VL = "";
					IS_R="";
					SP = "";
					EP = "";
					PY = "";
					DA = "";
					T2 = "";
					SN = "";
					DO = "";
					UR = "";
					KW = "";
					AB = "";
					ER = "";
					line=br.readLine();
					continue;
				}
				if (line.contains("  - ")) 
				{
					String[] lineSplit = line.split("  - ");
					header = lineSplit[0].trim();
					if (lineSplit.length>1) 
					{
						value = lineSplit[1];
					}
					else
					{
						value="";
					}
					if(!(value.trim().equals("")))
					{
						headerList.add(header);
					}
				} 
				else
				{
					value = line;
				}
				switch (header) {
				case "TY":
					TY=TY+value;
					break;
				case "T1":
					T1=T1+value;
					break;
				case "AU":// extract and assign multiple authors name to single cell
					String[] auUn=value.split(", ");
					AU=AU+auUn[1].trim()+" "+auUn[0].trim()+" and ";
					break;
				case "BT":
					BT=BT+value;
					break;
				case "PB":
					PB=PB+value;
					break;
				case "CY":
					CY=CY+value;
					break;
				case "JO":
					JO=JO+value;
					break;
				case "VL":
					VL=VL+value;
					break;
				case "IS_R":
					IS_R=IS_R+value;
					break;
				case "SP":
					SP=SP+value;
					break;
				case "EP":
					EP=EP+value;
					break;	
				case "PY":
					PY=PY+value;
					break;
				case "DA":
					DA=DA+value;
					break;
				case "T2":
					T2=T2+value;
					break;
				case "SN":
					SN=SN+value;
					break;
				case "DO":
					DO=DO+value;
					break;
				case "UR":
					UR=UR+value;
					break;
				case "KW":
					KW=KW+value;
					break;
				case "AB":
					AB=AB+value;
					break;
				case "ER":
					ER=ER+value;
					break;
				}
				line = br.readLine();
			}
			br.close();
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
 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
	}

	}

}
