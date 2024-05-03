package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.dao.OrderDetailsDao;

import com.model.OrderDetails;
import com.utility.DBConnection;

public class OrderDetailsDaoImpl implements  OrderDetailsDao {

	 @Override
	    public boolean isEmpty() throws SQLException {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        boolean isEmpty = true;

	        try {
	            conn = DBConnection.dbConnect(); 
	            String query = "SELECT COUNT(*) FROM orderdetails"; 
	            pstmt = conn.prepareStatement(query);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                int count = rs.getInt(1);
	                isEmpty = (count == 0);
	            }
	        } finally {
	            
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

	        return isEmpty;
	    }

	 @Override
	 public List<OrderDetails> GetOrderDetailsByOrderId(int orderId) throws SQLException {
	     Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     List<OrderDetails> orderDetailsList = new ArrayList<>();

	     try {
	         conn = DBConnection.dbConnect(); 
	         String query = "SELECT * FROM orderdetails WHERE OrderId = ?"; 
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, orderId);
	         rs = pstmt.executeQuery();

	         while (rs.next()) {
	        	    OrderDetails orderDetails = new OrderDetails();
	        	 
	        	    orderDetails.setOrderDetailId(rs.getInt("OrderDetailId"));
	        	    orderDetails.setQuantity(rs.getInt("Quantity"));
	        	    
	        	    
	          	    orderDetailsList.add(orderDetails);
	        	}
	     } finally {
	         // Close resources
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

	     return orderDetailsList;
	 }

	@Override
	public List<Integer> getAllOrderIds() throws SQLException {
		// TODO Auto-generated method stub
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<Integer> orderIds = new ArrayList<>();
		    
		    try {
		        conn = DBConnection.dbConnect();
		        String query = "SELECT OrderId FROM orders";
		        pstmt = conn.prepareStatement(query);
		        rs = pstmt.executeQuery();
		        
		        while (rs.next()) {
		            int orderId = rs.getInt("OrderId");
		            orderIds.add(orderId);
		        }
		    
		    } finally {
		        // Close resources
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
		    
		    return orderIds;
	}

	@Override
	public int UpdateQuantity(int orderDetailId, int newQuantity) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    int rowsAffected = 0;

	    try {
	        conn = DBConnection.dbConnect();
	        String query = "UPDATE orderdetails SET Quantity = ? WHERE OrderDetailId = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, newQuantity);
	        pstmt.setInt(2, orderDetailId);
	        rowsAffected = pstmt.executeUpdate();
	    } finally {
	        // Close resources
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }

	    return rowsAffected;
	}



	
}




	
	


