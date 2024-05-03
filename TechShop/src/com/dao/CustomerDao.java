package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Customer;


public interface CustomerDao {
	 
	Customer GetCustomerDetails(int id) throws SQLException;


	List<Customer> UpdateCustomerInfo(int id, String firstName, String lastName, String phone, String address,String email) throws SQLException;


}
