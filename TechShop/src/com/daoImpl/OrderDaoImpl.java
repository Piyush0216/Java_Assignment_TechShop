package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.OrderDao;
import com.model.Order;
import com.utility.DBConnection;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<Order> CalculateTotalOders() throws SQLException {
		// TODO Auto-generated method stub
		    Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        List<Order> orderList = new ArrayList<>();
	        
	        try {
	        	 conn = DBConnection.dbConnect();
	        	String query = "SELECT o.OrderId, o.OrderDate, o.CustomerId,  p.Price AS TotalAmount " +
                 "FROM orders o " +
                 "INNER JOIN orderdetails od ON o.OrderId = od.OrderId " +
                 "INNER JOIN products p ON od.ProductId = p.ProductId " +
                 "GROUP BY o.OrderId, o.OrderDate, o.CustomerId";
                 ps = conn.prepareStatement(query);
                 rs = ps.executeQuery();
                 
                 while (rs.next()) {
                     Order order = new Order();
                     order.setOrderId(rs.getInt("OrderId"));
                     order.setOrderDate(rs.getTimestamp("OrderDate"));
                     order.setCustomerId(rs.getInt("CustomerId"));
                     order.setTotalAmount(rs.getDouble("TotalAmount"));
                     orderList.add(order);
                 }
	        	
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            
	        } finally {
	          
	            if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } 
		return orderList;
	}

	@Override
	public List<Integer> getAllOrderIds() throws SQLException {
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
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        
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
	public List<Order> CalculateTotalAmountByOrderId(int id) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Order> orders = new ArrayList<>();
	    
	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT o.OrderId, o.OrderDate, o.CustomerId, p.Price AS TotalAmount " +
	                       "FROM orders o " +
	                       "INNER JOIN orderdetails od ON o.OrderId = od.OrderId " +
	                       "INNER JOIN products p ON od.ProductId = p.ProductId " +
	                       "WHERE o.OrderId = ? " +
	                       "GROUP BY o.OrderId, o.OrderDate, o.CustomerId";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Order order = new Order();
	            order.setOrderId(rs.getInt("OrderId"));
	            order.setOrderDate(rs.getTimestamp("OrderDate"));
	            order.setCustomerId(rs.getInt("CustomerId"));
	            order.setTotalAmount(rs.getDouble("TotalAmount"));
	            orders.add(order);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        
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
	    
	    return orders;
	}

	@Override
	public Order GetOrderDetailsByOrderId(int id) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Order order = null;
	    
	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT o.OrderId, o.OrderDate, o.CustomerId, o.Status,SUM(p.Price) AS TotalAmount " +
	                       "FROM orders o " +
	                       "INNER JOIN orderdetails od ON o.OrderId = od.OrderId " +
	                       "INNER JOIN products p ON od.ProductId = p.ProductId " +
	                       "WHERE o.OrderId = ? " +
	                       "GROUP BY o.OrderId, o.OrderDate, o.CustomerId";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            order = new Order();
	            order.setOrderId(rs.getInt("OrderId"));
	            order.setOrderDate(rs.getTimestamp("OrderDate"));
	            order.setCustomerId(rs.getInt("CustomerId"));
	            order.setTotalAmount(rs.getDouble("TotalAmount"));
	            order.setStatus(rs.getString("Status"));
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	       
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
	    
	    return order;
	}

	@Override
	public boolean UpdateOrderStatusByOrderId(int id, String status) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    boolean updated = false;
	    
	    try {
	        conn = DBConnection.dbConnect();
	        String query = "UPDATE orders SET Status = ? WHERE OrderId = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, status);
	        pstmt.setInt(2, id);
	        
	        int rowsAffected = pstmt.executeUpdate();
	        
	        // If one or more rows are affected, the update was successful
	        updated = rowsAffected > 0;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	       
	    } finally {
	        // Close resources
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	    
	    return updated;
	}

	@Override
	public boolean CancelOrderByOrderId(int id) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    
	    try {
	        conn = DBConnection.dbConnect();
	        String query = "UPDATE orders SET Status = ? WHERE OrderId = ?";
	        pstmt = conn.prepareStatement(query);
	        
	        // Set the OrderId
	        pstmt.setString(1,"Cancled" );
	        pstmt.setInt(2, id );
	        
	        int rowsAffected = pstmt.executeUpdate();
	        
	        // If one or more rows are affected, the cancellation was successful
	        return ( rowsAffected > 0) ;
	       
	        
	    }  finally {
	        // Close resources
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	    
	    
	}



	




}




	
	


