package com.service;

import java.sql.SQLException;
import java.util.List;
import com.dao.CustomerDao;
import com.dao.OrderDao;
import com.daoImpl.OrderDaoImpl;
import com.daoImpl.CustomerDaoImpl;
import com.model.Customer;
import com.model.Order;

public class CustomerService {
	
	OrderDao orderDao = new OrderDaoImpl();
	CustomerDao cstmrDao = new CustomerDaoImpl();

	public List<Order> CalculateTotalOders() throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.CalculateTotalOders();
	}

	public Customer GetCustomerDetails(int id) throws SQLException {
		// TODO Auto-generated method stub
		return cstmrDao.GetCustomerDetails(id);
	}

	public List<Customer> UpdateCustomerInfo(int id, String firstName, String lastName, String phone, String address, String email)  throws SQLException {
		// TODO Auto-generated method stub
		return cstmrDao.UpdateCustomerInfo(id, firstName, lastName, phone, address, email);
	}

}
