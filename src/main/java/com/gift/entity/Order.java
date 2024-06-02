package com.gift.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "orders")   
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String status;
	private Date orderDate;
   
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	public Order(int id, String status, Date orderDate, Cart cart) {
		super();
		this.id = id;
		this.status = status;
		this.orderDate = orderDate;
		this.cart = cart;
	}

	public Order() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	        
	

}
