package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Product;



public interface InventoryDao {

	List<Integer> getAllProductIds()throws SQLException;

	Product GetProduct(int productId)throws SQLException;

	int GetQuantityInStock(int productId)throws SQLException;

	List<Product> ListLowStockProducts(int id)throws SQLException;

	List<Product> ListOutOfStockProducts()throws SQLException;

	double GetInventoryValue()throws SQLException;

	boolean IsProductAvailable()throws SQLException;
	
	void AddToInventory(int productId, int quantity)throws SQLException; 
	
	void RemoveFromInventory(int productId, int quantity)throws SQLException;
	
	void UpdateStockQuantity(int productId, int newQuantity)throws SQLException; 
	
	

}
