package com.gift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Admin {
	@Id
	@GeneratedValue
	private int id;
	private String email;
	private String password;
	
	@OneToOne
	private Order order;  
	
}
