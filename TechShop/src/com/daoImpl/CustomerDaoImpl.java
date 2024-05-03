package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.CustomerDao;
import com.model.Customer;
import com.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao {


	@Override
	public Customer GetCustomerDetails(int id) throws SQLException {
		    Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Customer custmrd = null;        
	        try {
	        	 conn = DBConnection.dbConnect();
	        	String query = "Select * from customers where CustomerId = ?";
	        	
	        	pstmt = conn.prepareStatement(query);
	    		pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
              
                  if(rs.next()) {
                  custmrd = new Customer();
                  custmrd.setCustomerId(rs.getInt("CustomerId"));
                  custmrd.setFirstName(rs.getString("FirstName"));
                  custmrd.setLastName(rs.getString("LastName"));
                  custmrd.setPhone(rs.getString("Phone"));
                  custmrd.setAddress(rs.getString("Address"));
                  custmrd.setEmail(rs.getString("Email"));
                 }
	        	
	        }  finally {
	       
	        	if (rs != null) {
	                rs.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        }
	        return custmrd;

		
	}

	@Override
	public List<Customer> UpdateCustomerInfo(int id,String firstName, String lastName, String phone, String address, String email) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
        PreparedStatement pstmt = null;
      
        List<Customer> cstmr = new ArrayList<>();
        
        try {
        	 conn = DBConnection.dbConnect();
        	String query = "UPDATE Customers SET FirstName = ?,  LastName = ?,  Phone = ?,Address = ?, Email = ? WHERE CustomerID = ?";
        		       	
            pstmt = conn.prepareStatement(query);
    		
    		pstmt.setString(1,firstName);
    		pstmt.setString(2,lastName);
    		pstmt.setString(3,phone);
    		pstmt.setString(4,address);
    		pstmt.setString(5,email);
    		pstmt.setInt(6, id);
            pstmt.executeUpdate();
          
          
        	
        }  finally {
          
        	if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
		
		
		return cstmr;
		
	}
	
	

}
