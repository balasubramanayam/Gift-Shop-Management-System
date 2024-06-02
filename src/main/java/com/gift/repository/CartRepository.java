package com.gift.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.gift.entity.Cart;
import com.gift.entity.GiftList;
import com.gift.entity.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CartRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void insertCart(Cart cart) {
		entityManager.persist(cart);
	}

	@Transactional
	public void deleteById(int cartId) {
		Cart cart = entityManager.find(Cart.class, cartId);
		if (cart != null) {
			entityManager.remove(cart);
		}
	}

	public List<Cart> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Cart> query = currentSession.createQuery("from Cart", Cart.class);
		List<Cart> list = query.getResultList();
		return list;
	}


	
	@Transactional
	public void updateCart(Cart cart) {
		entityManager.merge(cart);
	}

	public Cart findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Cart cart = currentSession.get(Cart.class, id);
		return cart;
	}
	
	

	
	
	  public void deleteOrder(int cartId) {
	        Cart cart = entityManager.find(Cart.class, cartId);
	        if (cart != null) {
	            entityManager.remove(cart);
	        }
	  }  
	}


