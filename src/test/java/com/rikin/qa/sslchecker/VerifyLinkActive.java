package com.rikin.qa.sslchecker;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;

import org.testng.Assert;

public class VerifyLinkActive {
	public static HttpsURLConnection httpURLConnect;
	
	public static void handleCertificates(String initialUrl) throws IOException, CertificateExpiredException, CertificateNotYetValidException{

       
        if(initialUrl.contains("https://")) {
		      try { 
					      URL url = new URL(initialUrl);
					      httpURLConnect = (HttpsURLConnection)url.openConnection();
					      httpURLConnect.setConnectTimeout(3000);
					      httpURLConnect.connect();
		      
			  if(httpURLConnect.getResponseCode()==200) {
				    for(Certificate crt : httpURLConnect.getServerCertificates()) {					      
				    	  X509Certificate x509cert = (X509Certificate)crt;
					      BigInteger serialNum = x509cert.getSerialNumber();
					      String name = x509cert.getIssuerDN().getName();
					      System.out.println("Certificate status for domain : " + initialUrl);
					      System.out.println("This domain is secure - http response code : 200");
					      System.out.println("This is valid certificate");
					      System.out.println("Certificate Serial Number: 	" + serialNum);
					      System.out.println("Certificate Issuer Name/Info:		" + name);
					      System.out.println("Certificate valid form: " +x509cert.getNotBefore() + " to " + x509cert.getNotAfter());
					      x509cert.checkValidity();
					}
				 }else if(httpURLConnect.getResponseCode()==404){
					 System.out.println("This domain is secure - http response code : 404");
					   
				 }else if(httpURLConnect.getResponseCode()==500){ 
					 System.out.println("This domain is secure - http response code : 500");
			     }else{
			        	  System.out.println("This domain is secure - link Response code : " + httpURLConnect.getResponseCode());
			     }
			  
		      }catch (IOException e) {
		    	     System.out.println("This domain not having valid certificate/may be exprired for domain : " + initialUrl);
		    	     Assert.assertTrue(false);
			      }
	}else {
        try 
        {
           URL url = new URL(initialUrl);
           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
           httpURLConnect.setConnectTimeout(3000);
           httpURLConnect.connect();
           
           if(httpURLConnect.getResponseCode()==200)
           { System.out.println("This domain is not secure - http response code : 200");
            }
           else if(httpURLConnect.getResponseCode()==404)  
           {
        	 System.out.println("This domain is not secure - http response code : 404");
        	 
            }else if(httpURLConnect.getResponseCode()==500)
          { 
        	  System.out.println("This domain is not secure - http response code : 500");
        	  
          }else {
        	  
        	  System.out.println("This domain is not secure - http response code : " + httpURLConnect.getResponseCode());
          }
          
        } catch (Exception e) {
        	 fail("This domain is not secure / Not able to reach : " + initialUrl);
        }
	}
        }
}
