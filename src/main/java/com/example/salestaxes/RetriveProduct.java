package com.example.salestaxes;

import java.util.ArrayList;


public class RetriveProduct {
	enum ItemTypeList{
		BOOK("Book"),
		MUSIC_CD("Music CD"),
		CHOCOLATE("Chocolate");
		private String itemName;
		private ItemTypeList( String name){
			itemName = name;
		}

		public String getItemName(){
			return itemName;
		}
	}

	private static final String CHECK_IMPORTED = "imported";
	private ArrayList<Product> productsList = new ArrayList<Product>();
	
	public ArrayList<Product> retriveData(String data){
		
	

		// TODO Auto-generated method stub
		
	try {

			
    		String ar[]=data.split(System.getProperty("line.separator"));

    		for (int j = 0; j < ar.length; j++) {
            	String line = ar[j]; 
            	 
            	String[] words = line.split(" "); //divide the line into tokens
            	
            	int qty = Integer.parseInt(words[0]); //first token is the quantity
            	
            	boolean isImported = line.contains(CHECK_IMPORTED); //check if the item is imported
            	
            	String[] exemptedItems =  new String[]{"book","chocolate","pills"}; //check if the item in the exempted list
            	
            	int exemptedItemIndex = containsItemFromArray(line,exemptedItems); //Find which type of exemption
            	
            	String exemptedType = null;
            	
            	if(exemptedItemIndex != -1){
            		
                	exemptedType = exemptedItems[exemptedItemIndex];
        			
            	}

            	int splitIndex = line.lastIndexOf("at");
            	
            	if(splitIndex == -1){
            		
            		System.out.println("Bad Formatting");
            		
            	} else {
            		ItemTypeList list[] = ItemTypeList.values();

                	float price = Float.parseFloat((line.substring(splitIndex + 2))); //the price is the token after the substring "at"
                    
                	String name = line.substring(1, splitIndex); //the name is everything between the qty and at
                	
                    for(int i = 0;i<qty;i++){
                    	//loop for the total quantity of the item to make that many in the list
                    	
                    	Product newProduct = null;
                    	
                    	if(isImported){
                    		//the product is imported
                        	if(exemptedType != null){
                        		//the product is not imported and is exempt of sales tax
                        		
                        		
								
                        		if(exemptedType == "book"){
                        			newProduct = new Product(name,price,ItemType.IMPORTED_BOOK);
                        		} else if(exemptedType == "pills"){
                        			newProduct = new Product(name,price,ItemType.IMPORTED_MEDICAL);
                        		} else if(exemptedType == "chocolate"){
                        			newProduct = new Product(name,price,ItemType.IMPORTED_FOOD);
                        		}
                        		
                        		

                        	} else {
                        		//the product is imported and sales taxed
                        		newProduct = new Product(name,price,ItemType.IMPORTED_OTHERS);
                        	}
                        	
                    	} else {
                    		//the product is domestic
                        	if(exemptedType != null){
                        		//the product is domestic and is exempt of sales tax
                        		
                        		if(exemptedType == "book"){
                        			newProduct = new Product(name,price,ItemType.BOOK);
                        		} else if(exemptedType == "pills"){
                        			newProduct = new Product(name,price,ItemType.MEDICAL);
                        		} else if(exemptedType == "chocolate"){
                        			newProduct = new Product(name,price,ItemType.FOOD);
                        		}

                        	} else {
                        		//the product is domestic and is sales taxed
                        		newProduct = new Product(name,price,ItemType.OTHERS);
                        	}
                    	}
                    	//System.out.println("product---->"+newProduct);
                        productsList.add(newProduct); //add the product to our receipt's list
                    }
            	}
            	
            }
    		//System.out.println("product---->"+productsList);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		//return null;
	return productsList;
		
	}
	public static int containsItemFromArray(String inputString, String[] items) {
		/*
		 * This method returns the index of which String in items was found in the input String
		 *  -1 is returned in none of the Strings in items are found in the inputString
		 */
		int index = -1;
		
		for(int i = 0;i<items.length;i++){
			
			index = inputString.indexOf(items[i]);

			if(index != -1)
				return i;
				
		}
		return -1;
		
	}
}

