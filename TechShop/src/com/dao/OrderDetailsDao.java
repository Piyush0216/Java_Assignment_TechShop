package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.OrderDetails;

public interface OrderDetailsDao  {

	boolean isEmpty() throws SQLException;
	
	List<OrderDetails> GetOrderDetailsByOrderId(int orderId)throws SQLException;

	List<Integer> getAllOrderIds()throws SQLException;

	

	int UpdateQuantity(int orderDetailId, int newQuantity) throws SQLException;
	
	
}
