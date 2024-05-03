package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.InventoryDao;
import com.model.Product;
import com.utility.DBConnection;

public class InventoryDaoImpl implements  InventoryDao {

	@Override
	public List<Integer> getAllProductIds() throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Integer> productIds = new ArrayList<>();
	    
	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT productId FROM products";
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            productIds.add(rs.getInt("productId"));
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        
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
	    
	    return productIds;
	}


	@Override
	public Product GetProduct(int productId) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Product product = null;
	    
	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT * FROM products WHERE productId = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, productId);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            product = new Product();
	            product.setProductId(rs.getInt("productId"));
	            product.setProductName(rs.getString("productName"));
	            product.setDescription(rs.getString("description"));
	            product.setPrice(rs.getDouble("price"));
	            product.setStatus(rs.getString("status"));
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	       
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
	    
	    return product;
	}


	@Override
	public int GetQuantityInStock(int productId) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int quantity = 0;
	    
	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT QuantityInStocks FROM inventory WHERE productId = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, productId);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            quantity = rs.getInt("QuantityInStocks");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	      
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
	    
	    return quantity;
	}


	@Override
	public List<Product> ListLowStockProducts(int id) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Product> lowStockProducts = new ArrayList<>();

	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT productId, productName, description, price, status " +
	                       "FROM products JOIN inventory ON products.productId = inventory.productId " +
	                       "WHERE QuantityInStocks < ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Product product = new Product();
	            product.setProductId(rs.getInt("productId"));
	            product.setProductName(rs.getString("productName"));
	            product.setDescription(rs.getString("description"));
	            product.setPrice(rs.getDouble("price"));
	            product.setStatus(rs.getString("status"));
	            lowStockProducts.add(product);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	       
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

	    return lowStockProducts;
	}


	@Override
	public List<Product> ListOutOfStockProducts() throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Product> outOfStockProducts = new ArrayList<>();

	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT products.productId, products.productName, products.description, products.price, products.status " +
	                       "FROM products JOIN inventory ON products.productId = inventory.productId " +
	                        "WHERE products.status = 'OutOfStock'";
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Product product = new Product();
	            product.setProductId(rs.getInt("products.productId"));
	            product.setProductName(rs.getString("productName"));
	            product.setDescription(rs.getString("description"));
	            product.setPrice(rs.getDouble("price"));
	            product.setStatus(rs.getString("status"));
	            outOfStockProducts.add(product);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	      
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

	    return outOfStockProducts;
	}


	@Override
	public double GetInventoryValue() throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    double inventoryValue = 0.0;

	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT SUM(price * QuantityInStocks) AS inventoryValue " +
	                       "FROM products JOIN inventory ON products.productId = inventory.productId";
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            inventoryValue = rs.getDouble("inventoryValue");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	   
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

	    return inventoryValue;
	}

	@Override
	public boolean IsProductAvailable() throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    boolean productAvailable = false;

	    try {
	        conn = DBConnection.dbConnect();
	        String query = "SELECT COUNT(*) AS productCount " +
	                       "FROM inventory " +
	                       "WHERE QuantityInStocks > 0";
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            int productCount = rs.getInt("productCount");
	            productAvailable = (productCount > 0);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	  
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

	    return productAvailable;
	}


	@Override
	public void AddToInventory(int productId, int quantity) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DBConnection.dbConnect();
	        String query = "UPDATE inventory SET QuantityInStocks = QuantityInStocks + ? WHERE productId = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, quantity);
	        pstmt.setInt(2, productId);
	        pstmt.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	       
	    } finally {
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	}


	@Override
	public void RemoveFromInventory(int productId, int quantity) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DBConnection.dbConnect();
	        String query = "UPDATE inventory SET QuantityInStocks = QuantityInStocks - ? WHERE ProductId = ? AND QuantityInStocks >= ?";
	        
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, quantity);
	        pstmt.setInt(2, productId);
	        pstmt.setInt(3, quantity); // Ensure quantity to be removed is available
	        int rowsUpdated = pstmt.executeUpdate();
            
	        if (rowsUpdated == 0) {
	            throw new SQLException("Insufficient quantity in stock for product with ID: " + productId);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();

	    } finally {
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	}
	


	@Override
	public void UpdateStockQuantity(int productId, int newQuantity) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DBConnection.dbConnect();
	        String query = "UPDATE inventory SET QuantityInStocks = ? WHERE ProductId = ?";
	        
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, newQuantity);
	        pstmt.setInt(2, productId);
	        int rowsUpdated = pstmt.executeUpdate();

	        if (rowsUpdated == 0) {
	            throw new SQLException("Product with ID " + productId + " not found in the inventory.");
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
	
	


