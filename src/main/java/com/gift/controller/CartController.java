package com.gift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gift.entity.Cart;
import com.gift.service.CartService;


@CrossOrigin("http://localhost:3000/")
@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	@DeleteMapping("remove/{id}")
	public void removeFromCart(@PathVariable int id) {
		cartService.removeCart(id);
	}
	
	@PutMapping("/update/{itemId}")
	public ResponseEntity<?> updateCartItem(@PathVariable("itemId") int itemId, @RequestBody Cart request) {
		try {
			cartService.updateCartItem(itemId, request.getQuantity());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating cart item: " + e.getMessage());
		}
	}
	
	@GetMapping("/findAllCart")
	public List<Cart> findAllCart() {
		return cartService.getAll();
	}
	
	
	
	
		
		
	}

