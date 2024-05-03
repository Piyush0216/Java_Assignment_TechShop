package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.InventoryDao;
import com.daoImpl.InventoryDaoImpl;
import com.model.Product;


public class InventoryService {
	
	InventoryDao inventoryDao = new InventoryDaoImpl();

	public List<Integer> getAllProductIds()throws SQLException {
		// TODO Auto-generated method stub
		return inventoryDao.getAllProductIds();
	}

	public Product GetProduct(int productId)throws SQLException {
		// TODO Auto-generated method stub
		return inventoryDao.GetProduct(productId);
	}

	public int GetQuantityInStock(int productId)throws SQLException {
		// TODO Auto-generated method stub
		return inventoryDao.GetQuantityInStock(productId);
	}

	public void AddToInventory(int productId, int quantity)throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void RemoveFromInventory(int productId, int quantity)throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void UpdateStockQuantity(int productId, int newQuantity)throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public List<Product> ListLowStockProducts(int id)throws SQLException {
		// TODO Auto-generated method stub
		return inventoryDao.ListLowStockProducts(id);
	}

	public List<Product> ListOutOfStockProducts() throws SQLException{
		// TODO Auto-generated method stub
		return inventoryDao.ListOutOfStockProducts();
	}

	public double GetInventoryValue()throws SQLException {
		// TODO Auto-generated method stub
		return inventoryDao.GetInventoryValue();
	}

	public boolean IsProductAvailable(int productId) throws SQLException {
		// TODO Auto-generated method stub
		return inventoryDao.IsProductAvailable();
	}

	

}
