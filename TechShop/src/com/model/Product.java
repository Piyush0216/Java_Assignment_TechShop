package com.model;



public class Product {
	
	  private  int      ProductId;
	  private  String   ProductName;
	  private  String   Description;
	  private  Double   Price ;
	  private  String   Status;
	  
	  
	  
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {	
		Status = status;
	}
	
	public int getUnitPrice() {
        return Price != null ? Price.intValue() : 0;
    }

    public String getItemId() {
        return String.valueOf(ProductId);
    }

	  
	
	 
}
