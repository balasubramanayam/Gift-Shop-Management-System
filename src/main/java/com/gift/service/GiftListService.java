package com.gift.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gift.entity.Cart;
import com.gift.entity.GiftList;
import com.gift.entity.User;
import com.gift.repository.CartRepository;
import com.gift.repository.GiftListRepository;
import com.gift.repository.UserRepository;

@Service
@Transactional
public class GiftListService {

	@Autowired
	private GiftListRepository giftRepository;

	public void saveGiftWithImage(String name, int price, String description, MultipartFile imageFile)
			throws IOException {
		GiftList gift = new GiftList();
		gift.setName(name);
		gift.setPrice(price);
		gift.setDescription(description);
		gift.setImage(imageFile.getBytes());

		giftRepository.saveGiftWithImage(gift);
	}

	public List<GiftList> getAllGifts() {
		return giftRepository.getAllGifts();
	}

	public void updateGift(int id, String name, int price, String description, byte[] image) throws IOException {
		GiftList existingGift = giftRepository.getImageById(id);

		existingGift.setName(name);
		existingGift.setPrice(price);
		existingGift.setDescription(description);
		existingGift.setImage(image);

		giftRepository.updateGift(existingGift);
	}

	public GiftList getImageById(int id) {
		return giftRepository.getImageById(id);
	}

	public void deleteImage(int id) {
		giftRepository.deleteImage(id);
	}

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

//	@Transactional
//	public void addToCart(Cart cartRequest) {
//
//		if (cartRequest.getUser() == null || cartRequest.getGiftList() == null) {
//			throw new IllegalArgumentException("User or GiftList is null in the cart request.");
//		}
//
//		int userId = cartRequest.getUser().getUserId();
//		int giftId = cartRequest.getGiftList().getId();
//		int quantity = cartRequest.getQuantity();
//
//		User user = userRepository.getByUserId(userId);
//
//		GiftList gift = giftRepository.getImageById(giftId);
//
//		int totalPrice = gift.getPrice() * quantity;
//
//		int totalGifts = quantity;
//
//		Cart cart = new Cart();
//		cart.setUser(user);
//		cart.setGiftList(gift);
//		cart.setQuantity(quantity);
//		cart.setTotalPrice(totalPrice);
//		cart.setTotalGifts(totalGifts);
//
//		cartRepository.insertCart(cart);
//	}
//}

	
	
	
	
	
	@Transactional
	public void addToCart(Cart cartRequest) {
	    if (cartRequest.getUser() == null || cartRequest.getGiftList() == null) {
	        throw new IllegalArgumentException("User or GiftList is null in the cart request.");
	    }

	    int userId = cartRequest.getUser().getUserId();
	    int giftId = cartRequest.getGiftList().getId();
	    int quantity = cartRequest.getQuantity();

	    User user = userRepository.getByUserId(userId);
	    GiftList gift = giftRepository.getImageById(giftId);

	
	    Cart existingCart = cartRepository.findById(giftId);

	    if (existingCart != null) {
	    
	        throw new IllegalStateException("Gift is already in the cart.");
	    } else {
	       
	        int totalPrice = gift.getPrice() * quantity;
	        int totalGifts = quantity;

	        Cart cart = new Cart();
	        cart.setUser(user);
	        cart.setGiftList(gift);
	        cart.setQuantity(quantity);
	        cart.setTotalPrice(totalPrice);
	        cart.setTotalGifts(totalGifts);

	   
	        cartRepository.insertCart(cart);
	    }
	}

	
	

}