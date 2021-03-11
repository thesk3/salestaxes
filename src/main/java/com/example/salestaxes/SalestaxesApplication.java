package com.example.salestaxes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils; 
 
@SpringBootApplication
public class SalestaxesApplication {

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) throws IOException {
		//SpringApplication.run(SalestaxesApplication.class, args);
		
		
        


		String s1=passData("test1.txt");
		RetriveProduct rp=new RetriveProduct();
		ArrayList<Product> p1=rp.retriveData(s1);
		CalculateTax ct=new CalculateTax();
		System.out.println("Output 1");
		ct.calculateTotals(p1);
				//System.out.println("custom--->"+p1.toString());
				
		String s2=passData("test2.txt");
		RetriveProduct rp2=new RetriveProduct();
		ArrayList<Product> p2=rp.retriveData(s2);
		CalculateTax ct2=new CalculateTax();
		System.out.println("Output 2");
		ct2.calculateTotals(p2);
	
		String s3=passData("test3.txt");
		RetriveProduct rp3=new RetriveProduct();
		ArrayList<Product> p3=rp.retriveData(s3);
		CalculateTax ct3=new CalculateTax();
		System.out.println("Output 3");
		ct3.calculateTotals(p3);
	
		
		//		Receipt r1 = new Receipt(passData("test1.txt"));	
//		
//		r1.calculateTotals();
//		
//		System.out.println("Output 1");
//		r1.printReceipt();
//		System.out.println();
//
//		Receipt r2 = new Receipt(passData("test2.txt"));
//
//		r2.calculateTotals();
//		
//		System.out.println("Output 2");
//		r2.printReceipt();
//		System.out.println();
//		
//		Receipt r3 = new Receipt(passData("test3.txt"));
//		
//		r3.calculateTotals();
//		
//		System.out.println("Output 3");
//		r3.printReceipt();
		
	}
	 private static String passData(String string) throws IOException {
		// TODO Auto-generated method stub
		    Resource resource = new ClassPathResource(string);
	        InputStream inputStream = resource.getInputStream();
	        String data ="";
	        try {
	            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
	            data = new String(bdata, StandardCharsets.UTF_8);
	            
	        } catch (IOException e) {
	        	
	        }

		return data;
	}

}
