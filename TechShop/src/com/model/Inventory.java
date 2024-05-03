package com.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
	
	private int  InventoryId ;
	private int  QuantityInStocks;
	private int  ProductId;
	private Date LastStockUpdate;
	private List<Product> products;
	
	public Inventory() {
		setProducts(new ArrayList<>());
	}
	
	public int getInventoryId() {
		return InventoryId;
	}
	public void setInventoryId(int inventoryId) {
		InventoryId = inventoryId;
	}
	public int getQuantityInStocks() {
		return QuantityInStocks;
	}
	public void setQuantityInStocks(int quantityInStocks) {
		QuantityInStocks = quantityInStocks;
	}
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}
	public Date getLastStockUpdate() {
		return LastStockUpdate;
	}
	public void setLastStockUpdate(Date lastStockUpdate) {
		LastStockUpdate = lastStockUpdate;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	} 
	
	
	
}
