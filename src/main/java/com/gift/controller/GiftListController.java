package com.gift.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gift.entity.Cart;
import com.gift.entity.GiftList;
import com.gift.service.GiftListService;

@CrossOrigin("http://localhost:3000/")
@RestController
public class GiftListController {

	@Autowired
	private GiftListService giftService;

	@PostMapping("/insertgifts")
	public ResponseEntity<String> uploadGiftWithImage(@RequestParam("name") String name,
			@RequestParam("price") int price, @RequestParam("description") String description,
			@RequestParam("image") MultipartFile imageFile) {
		try {
			giftService.saveGiftWithImage(name, price, description, imageFile);
			return ResponseEntity.ok("Gift uploaded successfully!");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while uploading the gift.");
		}
	}

	@GetMapping("/giftall")
	public ResponseEntity<List<GiftList>> getAllGifts() {
		List<GiftList> gifts = giftService.getAllGifts();
		return new ResponseEntity<>(gifts, HttpStatus.OK);
	}

	@PutMapping("/updategift/{id}")
	public ResponseEntity<String> updateGift(@PathVariable("id") int id, @RequestParam("name") String name,
			@RequestParam("price") int price, @RequestParam("description") String description,
			@RequestParam("image") MultipartFile image) {
		try {
			byte[] imageData = image.getBytes();
			giftService.updateGift(id, name, price, description, imageData);
			return ResponseEntity.ok("Gift updated successfully!");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while updating the gift.");
		}
	}

	 @GetMapping("/getById/{id}")
	    public ResponseEntity<?> getImageById(@PathVariable int id) {
	        try {
	            GiftList gift = giftService.getImageById(id);
	            if (gift != null) {
	                return ResponseEntity.ok(gift);
	            } else {
	                return ResponseEntity.notFound().build();   
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }


	@DeleteMapping("/deleteImage/{id}")
	public ResponseEntity<String> deleteImage(@PathVariable int id) {
		try {
			giftService.deleteImage(id);
			return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to delete image", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/addCart")
	public ResponseEntity<String> insertCart(@RequestBody Cart cart) {
			giftService.addToCart(cart);
			return ResponseEntity.ok("added");
		}
 
}
