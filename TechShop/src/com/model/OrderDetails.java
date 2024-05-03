package com.model;



public class OrderDetails {
	
	private int OrderDetailId  ;
	private int Quantity;
	private Order Order ;
	private Product product;
	
	
	public int getOrderDetailId() {
		return OrderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		OrderDetailId = orderDetailId;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public Order getOrder() {
		return Order;
	}
	public void setOrder(Order order) {
		Order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getUnitPrice() {
       
        if (product != null) {
            return product.getUnitPrice();
        } else {
            return 0; 
       }
    }

    public String getItemId() {
       
        if (product != null) {
            return product.getItemId();
        } else {
            return null;
        }
    }
	
	
	 
	
	 
	
	
	 
}
