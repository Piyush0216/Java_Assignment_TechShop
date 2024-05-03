package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.ProductDao;
import com.daoImpl.ProductDaoImpl;
import com.model.Product;


public class ProductService {
	
	ProductDao productDao = new ProductDaoImpl();

	public Product GetProductDetails(int id) throws SQLException {
		// TODO Auto-generated method stub
		return productDao.GetProductDetails(id);
	}

	public boolean UpdateProductInfo(int id, String productName, String description, Double price, String status)throws SQLException  {
		// TODO Auto-generated method stub
				return productDao.UpdateProductInfo(id,productName,description,price,status);
		
	}

	public List<Integer> getAllProductIds() throws SQLException {
		// TODO Auto-generated method stub
		return productDao.getAllProductIds();
	}

	public int IsProductInStock(int id) throws SQLException {
		// TODO Auto-generated method stub
		
		return productDao.IsProductInStock(id);
		
	}

	

}
