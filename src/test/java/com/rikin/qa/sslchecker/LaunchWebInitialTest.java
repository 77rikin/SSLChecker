package com.rikin.qa.sslchecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LaunchWebInitialTest {
	WebDriver driver;
	String initialUrl;

	@Test(dataProvider = "getData")
	public void appWebLaunchTest(String link) throws Exception {
		driver = new HtmlUnitDriver();
		try{
		driver.get(link.toString());
			String url = driver.getCurrentUrl();
			//Method to verify HTTP Errors on links
     	    VerifyLinkActive.handleCertificates(url);			
		} catch(Exception e){
			System.out.println(e.getMessage());
		  }
		    System.out.println("----------------End started----------------");
		 }
	
	@DataProvider
	public Iterator<Object[]> getData() throws IOException {
		ArrayList<Object[]> testData=  ReadCSVFile.readCSV();
		return testData.iterator();
	}
	 
	@AfterMethod
	public void tearDown() {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
