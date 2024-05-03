package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.ProductDao;
import com.model.Product;
import com.utility.DBConnection;

public class ProductDaoImpl implements ProductDao {

	@Override
	public Product GetProductDetails(int id) throws SQLException {
		// TODO Auto-generated method stub
		    Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Product prdct = null;        
	        try {
	        	 conn = DBConnection.dbConnect();
	        	String query = "Select * from products where productId = ?";
	        	
	        	pstmt = conn.prepareStatement(query);
	    		pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
           
               if(rs.next()) {
            	   prdct = new Product();
            	   prdct.setProductId(rs.getInt("ProductId"));
            	   prdct.setProductName(rs.getString("ProductName"));
            	   prdct.setDescription(rs.getString("Description"));
            	   prdct.setPrice(rs.getDouble("Price"));
            	   prdct.setStatus(rs.getString("Status"));
            	   
            	  
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
	        return prdct;

	}

	@Override
	public boolean UpdateProductInfo(int id, String productName, String description, Double price ,String Status) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
        PreparedStatement pstmt = null;
      
        
        
        try {
        	 conn = DBConnection.dbConnect();
        	String query = "UPDATE products SET ProductName = ?,  Description = ?,  Price = ?, Status = ? WHERE ProductID = ?";
        		       	
            pstmt = conn.prepareStatement(query);
    		
    		pstmt.setString(1,productName);
    		pstmt.setString(2,description);
    		pstmt.setDouble(3,price);
    		pstmt.setString(4,Status);
       		pstmt.setInt(5, id);
       		int rowEffected =  pstmt.executeUpdate();
       		
       		if(rowEffected > 0) {
       			return true;
       		}
       		else {
       			return false;
       		}
       	                   
        	
        }  finally {
          
        	if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
		
		
	}

	@Override
	public List<Integer> getAllProductIds() throws SQLException {
		// TODO Auto-generated method stub
		  Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<Integer> productIds = new ArrayList<>();
		    
		    try {
		        conn = DBConnection.dbConnect();
		        String query = "SELECT ProductId FROM products";
		        pstmt = conn.prepareStatement(query);
		        rs = pstmt.executeQuery();
		        
		        while (rs.next()) {
		            int productId = rs.getInt("ProductId");
		            productIds.add(productId);
		        }
		    }  finally {
		       
		        try {
		            if (rs != null) {
		                rs.close();
		            }
		            if (pstmt != null) {
		                pstmt.close();
		            }
		            if (conn != null) {
		                conn.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return productIds;
	}

	@Override
	public int IsProductInStock(int id) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    	    
	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT Status FROM products WHERE ProductId = ? ";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();
	        String status = null;
	        
	        if (rs.next()) {
	            status = rs.getString("Status");
	       } 
	        if(status == null) return 2;
	        
	        if(status.equalsIgnoreCase("InStock")) {
	        	return 1;
	        }
	        else {
	        	
	        	return 0;
	        }
	        
	    }  finally {
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
	    
	  
	}

	@Override
	public boolean AddProduct(String productName, String poductDescription, int productPrice) throws SQLException {
		Connection conn = null;
        PreparedStatement pstmt = null;
      
        
        
        try {
        	 conn = DBConnection.dbConnect();
        	String query = "Insert into products(ProductName,Description,Price,Status) values (?,?,?,?)";
        		       	
            pstmt = conn.prepareStatement(query);
    		
    		pstmt.setString(1,productName);
    		pstmt.setString(2,poductDescription);
    		pstmt.setDouble(3,productPrice);
    		pstmt.setString(4,"InStock");
       	
       		int rowEffected =  pstmt.executeUpdate();
       		
       		if(rowEffected > 0) {
       			return true;
       		}
       		else {
       			return false;
       		}
       	                   
        	
        }  finally {
          
        	if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
		
	}



	
}
	
	


