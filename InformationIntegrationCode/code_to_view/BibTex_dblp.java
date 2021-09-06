package com.II.readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class  BibTex_dblp
{

	public static void read(String TestFile) throws IOException 
	{
		Connection connection = null;

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

			

				File file = new File(TestFile);
				BufferedReader br = new BufferedReader(new FileReader(file));
				ArrayList<String> headerList = new ArrayList<String>();
	
				String header = "";
				String value = "";
				String line = "";
				String article_id = "";
				String author = "";
				String editor = "";
				String title = "";
				String school = "";
				String booktitle = "";
				String series = "";
				String journal = "";
				String volume = "";
				String number_t = "";
				String pages = "";
				String publisher = "";
				String year = "";
				String note = "";
				String url = "";
				String doi = "";
				String archivePrefix = "";
				String eprint = "";
				String isbn = "";
				String timestamp = "";
				String biburl = "";
				String bibsource = "";
	
				while (line != null) 
				{
					line=line.replace("  number    = ", "  number_t    = ");
					//'number' is predefined variable in sql 
					if (line.trim().equals("}")) // replace all special characters when parsing bib file 
					{
						line=br.readLine();
						continue;
					}
					line=line.replace("{", "").replace("},", "").replace("}", "");

					if (line.trim().equals("")) 
					{
						connection=DriverManager.getConnection(  
					    		"jdbc:oracle:thin:@localhost:1521:orcl","II_project","II_project");
					        connection.setAutoCommit(false);
						int count=1;
				        Set<String> set = new LinkedHashSet<String>();
				        set.addAll(headerList);// using set to remove duplicate headers
				        headerList.clear();//// clear all the previous values from headerList
				        headerList.addAll(set);//add fresh values to headerList without duplicates
				        headerList.add("SOURCE");
				        ArrayList<String> list = new ArrayList<String>(Collections.nCopies(headerList.size(), "?"));
						//generate insert query
				        String query="Insert into BIBTEX_DBLP ";
						query=query+headerList.toString().replace("[", "(").replace("]", ")");
						query =query+" values "+list.toString().replace("[", "(").replace("]", ")");
			            System.out.println(query);
			            String[] keyArray=author.trim().split(" and ");
			            
						// Extracting multiple authors name to generate key such that:
						//First author's last name+other authors' first letters of their last names+year of publication

			            String key="";
			            if(author.length()>0){
			            	for(String keyEach:keyArray){
				            	if(key.trim().length()==0){
				            		String[] temp=keyEach.split(" ");
				            		key=key+temp[temp.length-1].trim();
				            	}else{
				            		String[] temp=keyEach.split(" ");
				            		key=key+temp[temp.length-1].trim().charAt(0);
				            	}
				            }
				            key=(key+year).replaceAll("\\\\", "").replaceAll("'", "").replaceAll("-", "").replaceAll("~", "").replaceAll("\"", "");	
			            }
						PreparedStatement stmt = connection.prepareStatement(query);
						// Assign values to the columns only when the column exists
						if (!article_id.equals(""))
						{	
			                stmt.setString(count, key);
			                ++count;
						}
						if (!author.equals(""))
						{	
			                stmt.setString(count, author);
			                ++count;
						}
						if (!editor.equals(""))
						{
			                stmt.setString(count, editor);
			                ++count;

						}
						if (!title.equals(""))
						{
			                stmt.setString(count, title);
			                ++count;

						}
						if (!school.equals(""))
						{

			                stmt.setString(count, school);
			                ++count;

						}
						if (!booktitle.equals(""))
						{

			                stmt.setString(count, booktitle);
			                ++count;

						}
						if (!series.equals(""))
						{

			                stmt.setString(count, series);
			                ++count;

						}
						if (!journal.equals(""))
						{

			                stmt.setString(count, journal);
			                ++count;

						}
						if (!volume.equals(""))
						{

			                stmt.setString(count, volume);
			                ++count;

						}
						if (!number_t.equals(""))
						{
			                stmt.setString(count, number_t);
			                ++count;

						}
						if (!pages.equals(""))
						{

			                stmt.setString(count, pages);
			                ++count;

						}
						if (!publisher.equals(""))
						{
						
			                stmt.setString(count, publisher);
			                ++count;
			                
						}
						if (!year.equals(""))
						{

			                stmt.setString(count, year);
			                ++count;

						}
						if (!note.equals(""))
						{

			                stmt.setString(count, note);
			                ++count;

						}
						if (!url.equals(""))
						{

			                stmt.setString(count, url);
			                ++count;

						}
						if (!doi.equals(""))
						{

			                stmt.setString(count, doi);
			                ++count;

						}
						if (!archivePrefix.equals(""))
						{

			                stmt.setString(count, archivePrefix);
			                ++count;

						}
						if (!eprint.equals(""))
						{

			                stmt.setString(count, eprint);
			                ++count;

						}
						if (!isbn.equals(""))
						{

			                stmt.setString(count, isbn);
			                ++count;

						}
						if (!timestamp.equals(""))
						{

			                stmt.setString(count, timestamp);
			                ++count;

						}
						if (!biburl.equals(""))
						{

			                stmt.setString(count, biburl);
			                ++count;

						}
						if (!bibsource.equals(""))
						{

			                stmt.setString(count, bibsource);
			                ++count;

						}
						stmt.setString(count, "BIB");
						stmt.execute();
						headerList=new ArrayList<String>();
						article_id="";
	  					 author = "";
				         editor = "";
				         title = "";
				         school = "";
				         booktitle = "";
				         series = "";
				         journal = "";
						 volume = "";
						 number_t = "";
						 pages = "";
						 publisher = "";
						 year = "";
						 note = "";
						 url = "";
						 doi = "";
						 archivePrefix = "";
						 eprint = "";
						 isbn = "";
						 timestamp = "";
						 biburl = "";
						 bibsource = "";
						 line=br.readLine();
							connection.commit();
				            connection.close();
						 continue;
					}
					if (line.charAt(0) == '@') 
					{
						value = line;
						header="article_id";
						headerList.add("article_id");
					}
	
					if (line.contains(" = ")) 
					{
						String[] lineSplit = line.split(" = ");
						header = lineSplit[0].trim();
						if (lineSplit.length > 1) 
						{
							value = lineSplit[1];
						} else 
						{
							value = "";
						}
						if (!(value.trim().equals(""))) 
						{
							headerList.add(header);
						}
					} 
					else 
					{
						value = line;
					}
					switch (header) 
					{
						case "article_id":
							article_id = article_id + value;
							break;
						case "author":
							if(author.trim().length()==0){
								author = author + value.trim();
							}
							else{
								author = author +" "+ value.trim();
							}
							break;
						case "editor":
							editor = editor + value;
							break;
						case "title":// extract and assign title from multiple lines to a single cell
							if(title.trim().length()==0){
								title = value.trim();
							}else{
								title=title.trim()+" "+value.trim();
							}
							break;
						case "school":
							school = school + value;
							break;
						case "booktitle":
							booktitle = booktitle + value;
							break;
						case "series":
							series = series + value;
							break;
						case "journal":
							journal = journal + value;
							break;
						case "volume":
							volume = volume + value;
							break;
						case "number_t":
							number_t = number_t + value;
							break;
						case "pages":
							pages = pages + value;
							break;
						case "publisher":
							publisher = publisher + value;
							break;
						case "year":
							year = year + value;
							break;
						case "note":
							note = note + value;
							break;
						case "url":
							url = url + value;
							break;
						case "doi":
							doi = doi + value;
							break;
						case "archivePrefix":
							archivePrefix = archivePrefix + value;
							break;
						case "eprint":
							eprint = eprint + value;
							break;
						case "isbn":
							isbn = isbn + value;
							break;
						case "timestamp":
							timestamp = timestamp + value;
							break;
						case "biburl":
							biburl = biburl + value;
							break;
						case "bibsource":
							bibsource = bibsource + value;
							break;
					}
					line = br.readLine();
				}
				br.close();
			} 
			catch (SQLException ex) 
			{
	            ex.printStackTrace();
	            try 
	            {
	                connection.rollback();
	            } 
	            catch (SQLException e) 
	            {
	                e.printStackTrace();
	            }
	        }
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException 
	{
		String TestFile = "D:\\PG_MS\\Sem1\\Information_Integration\\Project\\Data_Source\\dblp_Data.bib";
		read(TestFile);

	}

}
