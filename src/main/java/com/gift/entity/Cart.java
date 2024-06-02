package com.gift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity  
@Table(name = "cart")     
public class Cart {   

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "giftlist_id")
	private GiftList GiftList;
	private int totalPrice;
	private int totalGifts;
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {      
		this.id = id;
	}

	public GiftList getGiftList() {
		return GiftList;
	}

	public void setGiftList(GiftList giftList) {
		GiftList = giftList;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart() {
		super();
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalGifts() {
		return totalGifts;
	}

	public void setTotalGifts(int totalGifts) {
		this.totalGifts = totalGifts;
	}

	public Cart(int id, com.gift.entity.GiftList giftList, int quantity, User user, int totalPrice, int totalGifts) {
		super();
		this.id = id;
		GiftList = giftList;
		this.quantity = quantity;
		this.user = user;
		this.totalPrice = totalPrice;
		this.totalGifts = totalGifts;
	}

}
