package com.gift.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.gift.entity.Order;
import com.gift.service.OrderService;

@CrossOrigin("http://localhost:3000/")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/addOrder")
	public ResponseEntity<String> addToOrder(@RequestBody Order orderRequest) {
	    try {
	        orderService.addToOrder(orderRequest);
	        return ResponseEntity.ok("Order added successfully with ID: " + orderRequest.getId());
	    } catch (IllegalArgumentException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}  
	
	
	  @GetMapping("/getAllOrders")
	    public List<Order> getAllOrders() {
	        return orderService.getAllOrders();
	    }
	  
	  
	  
	  @PutMapping("/updateOrderStatus/{orderId}")
	  public ResponseEntity<String> updateOrderStatus(@PathVariable int orderId, @RequestBody String status) {
	      boolean success = orderService.updateOrderStatus(orderId, status);
	      if (success) {
	          return ResponseEntity.ok("Order status updated successfully");
	      } else {
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update order status");
	      }
	  }

	       
	  @GetMapping("/getByOrderId/{orderId}")
	    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") int id) {
	        Optional<Order> optionalOrder = orderService.getOrderById(id);

	        if (optionalOrder.isPresent()) {
	            Order order = optionalOrder.get();
	            return ResponseEntity.ok(order);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	  
	  
	  
	  @DeleteMapping("/deleteOrder/{id}")
	    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
	        try {
	            orderService.deleteOrder(id); 
	            return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Failed to delete ", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
