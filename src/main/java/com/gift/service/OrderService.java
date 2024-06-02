package com.gift.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gift.entity.Cart;
import com.gift.entity.Order;
import com.gift.repository.CartRepository;
import com.gift.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public void addToOrder(Order orderRequest) {
	    if (orderRequest.getCart() == null) {
	        throw new IllegalArgumentException("Cart is null in the order request.");
	    }

	    Cart cart = orderRequest.getCart();
	    String status = orderRequest.getStatus();
	    Date date = orderRequest.getOrderDate();
    
	    Cart persistedCart = cartRepository.findById(cart.getId());

	    Order order = new Order();
	    order.setCart(persistedCart);
	    order.setOrderDate(date);
	    order.setStatus(status);
        orderRepository.insertOrder(order);
	   
	    }
	


	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}

	@Transactional
	public boolean updateOrderStatus(int orderId, String status) {
		try {
			Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
			if (optionalOrder.isPresent()) {
				Order order = optionalOrder.get();
				order.setStatus(status);
				orderRepository.updateStatus(order);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public Optional<Order> getOrderById(int id) {
		return orderRepository.findByOrderId(id);
	}

	
	
	 @Transactional
	    public void deleteOrder(int id) {
	        orderRepository.deleteOrderById(id);
	    }
	 

}
