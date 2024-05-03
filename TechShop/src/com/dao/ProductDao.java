package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Product;



public interface ProductDao {

	Product GetProductDetails(int id) throws SQLException;

	boolean UpdateProductInfo(int id, String productName, String description, Double price,String Status) throws SQLException;

	List<Integer> getAllProductIds() throws SQLException;
	
	int IsProductInStock(int id)throws SQLException;

	boolean AddProduct(String productName, String poductDescription, int productPrice) throws SQLException;

	
	 
	

}
