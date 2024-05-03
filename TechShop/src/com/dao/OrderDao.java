package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Order;

public interface OrderDao {
	
	 List<Order> CalculateTotalOders() throws SQLException;
	 
	 List<Integer> getAllOrderIds() throws SQLException ;

	 List<Order> CalculateTotalAmountByOrderId(int id) throws SQLException ;

	 Order GetOrderDetailsByOrderId(int id) throws SQLException;

	boolean UpdateOrderStatusByOrderId(int id, String status)throws SQLException;

	boolean CancelOrderByOrderId(int id) throws SQLException ;
}
