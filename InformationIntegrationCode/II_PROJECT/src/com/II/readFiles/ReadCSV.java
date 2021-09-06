package com.II.readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV 
{
	 public static final String delimiter = ",";
	 public static void read(String csvFile) 
	 {
		 try {
	         File file = new File(csvFile);
	         FileReader fr = new FileReader(file);
	         BufferedReader br = new BufferedReader(fr);
	         String line = "";
	         String[] tempArr;
	         while((line = br.readLine()) != null) {
	            tempArr = line.split(delimiter);
	            System.out.println("Title: " + tempArr[0] + " , Authors=" + tempArr[1]);	            
	            System.out.println();
	         }
	         br.close();
	         } catch(IOException ioe) {
	            ioe.printStackTrace();
	         }
	 }

 
public static void main(String[] args) 
	{
		String csvFile = "D:\\PG_MS\\Sem1\\Information_Integration\\Project\\Data_Source\\IEEE_Data.csv";
	    read(csvFile);


	}

}
