package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.OrderDetailsDao;
import com.daoImpl.OrderDetailsDaoImpl;
import com.model.OrderDetails;




public class OrderDetailsService {
	
	static OrderDetailsDao orderDetailsDao = new OrderDetailsDaoImpl();

	public boolean isEmpty() throws SQLException {
		// TODO Auto-generated method stub
		return orderDetailsDao.isEmpty();
	}

	public static  List<OrderDetails> GetOrderDetailsByOrderId(int orderId) throws SQLException {
		// TODO Auto-generated method stub
		return orderDetailsDao.GetOrderDetailsByOrderId(orderId);
	}

	public List<Integer> getAllProductIds() throws SQLException {
		// TODO Auto-generated method stub
		 return orderDetailsDao.getAllOrderIds();
	}

	public int UpdateQuantity(int id, int quantity) throws SQLException{
		// TODO Auto-generated method stub
		
		return orderDetailsDao.UpdateQuantity(id, quantity);
		
	}
	
	

	

	
	
}
