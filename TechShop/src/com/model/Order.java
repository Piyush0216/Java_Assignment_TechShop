package com.model;

import java.sql.Timestamp;

public class Order {
	
	private int OrderId  ;
	private int CustomerId;
	private Timestamp OrderDate ;
	private double TotalAmount;
	private String Status;
	
	 
	 
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}
	public Timestamp getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Timestamp timestamp) {
		OrderDate = timestamp;
	}
	public double getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(double d) {
		TotalAmount = d;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	 
}
