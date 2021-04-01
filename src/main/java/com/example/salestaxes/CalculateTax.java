package com.example.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculateTax implements TaxCalculatorIntf{
	private double total;
	private double taxTotal;
	
	public void calculateTotals(ArrayList<Product> productsList){
		/*
		 * This method runs through the receipt's list of products in order to calculate the sales tax and total
		 * BigDecimals are used in order to avoid rounding errors
		 * 
		 */
		int numOfItems = productsList.size();
		
		BigDecimal runningSum = new BigDecimal("0");
		BigDecimal runningTaxSum = new BigDecimal("0");
		
		for(int i = 0;i<numOfItems;i++){
			
			runningTaxSum = BigDecimal.valueOf(0);
			
			BigDecimal totalBeforeTax = new BigDecimal(String.valueOf(productsList.get(i).getPrice()));
			
			runningSum = runningSum.add(totalBeforeTax);

			if(productsList.get(i).isSalesTaxable()){
				//This item is sales taxable so charge 10% tax and round to the nearest 0.05
			
			    BigDecimal salesTaxPercent = new BigDecimal(".10");
			    BigDecimal salesTax = salesTaxPercent.multiply(totalBeforeTax);
			    
			    salesTax = round(salesTax, BigDecimal.valueOf(0.05), RoundingMode.UP);
			    runningTaxSum = runningTaxSum.add(salesTax);
			    
    
			} 
			
			if(productsList.get(i).isImportedTaxable()){
				//this item is import taxable so charge 5% tax and round to the nearest 0.05

			    BigDecimal importTaxPercent = new BigDecimal(".05");
			    BigDecimal importTax = importTaxPercent.multiply(totalBeforeTax);
			    
			    importTax = round(importTax, BigDecimal.valueOf(0.05), RoundingMode.UP);
			    runningTaxSum = runningTaxSum.add(importTax);
			   
			}

			
			productsList.get(i).setPrice(runningTaxSum.floatValue() + productsList.get(i).getPrice());
		
			taxTotal += runningTaxSum.doubleValue();
			
			runningSum = runningSum.add(runningTaxSum);
		}
			//save out sales tax, and total
			taxTotal = roundTwoDecimals(taxTotal);
			total = runningSum.doubleValue();
			
			Receipt rc=new Receipt();
			System.out.println();
			rc.printReceiptProduct(productsList,taxTotal ,total );
	}		
	
	public static BigDecimal round(BigDecimal value, BigDecimal increment,RoundingMode roundingMode) {
		/*
		 * This method handles custom rounding to 0.05, and also sets the BigDecimal numbers to use 2 decimals
		 * 
		 */
		if (increment.signum() == 0) {
		// 0 increment does not make much sense, but prevent division by 0
		return value;
		} else {
			BigDecimal divided = value.divide(increment, 0, roundingMode);
			BigDecimal result = divided.multiply(increment);
			result.setScale(2, RoundingMode.UNNECESSARY);
			return result;
		}
	}
	
	public double roundTwoDecimals(double d) {
		//A rounding method for double values to 2 decimals
	    DecimalFormat twoDForm = new DecimalFormat("#.##");
	    return Double.valueOf(twoDForm.format(d));
	}
	
	public void setTotal(BigDecimal amount){
		total = amount.doubleValue();
	}
	
	public double getTotal(){
		return total;
	}
	public void setSalesTaxTotal(BigDecimal amount){
		taxTotal = amount.doubleValue();
	}
	
	public double getSalesTaxTotal(){
		return taxTotal;
	}

}
