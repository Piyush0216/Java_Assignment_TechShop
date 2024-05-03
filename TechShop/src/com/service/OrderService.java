package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.OrderDao;
import com.daoImpl.OrderDaoImpl;
import com.model.Order;




public class OrderService {
	
	OrderDao orderDao = new OrderDaoImpl();

	public List<Integer> getAllOrderIds() throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.getAllOrderIds();
	}

	public List<Order> CalculateTotalAmountByOrderId(int id) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.CalculateTotalAmountByOrderId(id);
	}

	public Order GetOrderDetailsByOrderId(int id) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.GetOrderDetailsByOrderId(id);
	}

	public boolean UpdateOrderStatusByOrderId(int id, String status) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.UpdateOrderStatusByOrderId(id,status);
	}

	public boolean CancelOrderByOrderId(int id) throws SQLException  {
		// TODO Auto-generated method stub
		return orderDao.CancelOrderByOrderId(id) ;
	}

	
	
}
