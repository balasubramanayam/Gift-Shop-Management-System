package com.gift.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.entity.Cart;
import com.gift.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	public List<Cart> getAll() {
		return cartRepository.getAll();
	}

	public void removeCart(int id) {
		cartRepository.deleteById(id);
	}

	public void updateCartItem(int itemId, int newQuantity) {
		Cart optionalCart = cartRepository.findById(itemId);
		if (optionalCart!=null) {
			
			optionalCart.setQuantity(newQuantity);
			int totalPrice = optionalCart.getGiftList().getPrice() * newQuantity;
			int totalGifts = newQuantity;
			optionalCart.setTotalPrice(totalPrice);
			optionalCart.setTotalGifts(totalGifts);
			cartRepository.updateCart(optionalCart);
		} else {
			throw new IllegalArgumentException("Cart item with id " + itemId + " not found");
		}
	}

	public Cart getById(int id) {

		return cartRepository.findById(id);
	}

	public void deleteCart(int id) {
		cartRepository.deleteById(id);
	}
	
	

}
