package com.rikin.qa.sslchecker;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class ReadCSVFile {
	
	public static String str;
	public static CSVReader reader;

	@Test
	public static ArrayList<Object[]> readCSV() throws IOException {
		try {
		reader = new CSVReader(new FileReader(".//src//test//resources//test.csv"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		List<String[]> allRows = reader.readAll();
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		for(String[] row : allRows){
			String str = row[0].toString();
	        Object columns[] = {str};
	        myData.add(columns);
	     }
		return myData;
	}
}
