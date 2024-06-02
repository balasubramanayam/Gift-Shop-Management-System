package com.gift.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.gift.entity.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class OrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void insertOrder(Order order) {
		entityManager.merge(order);
	}

	public Optional<Order> findByCartId(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Order order = currentSession.createQuery("FROM Order o WHERE o.cart.id = :cartId", Order.class)
				.setParameter("cartId", id).uniqueResult();
		return Optional.ofNullable(order);
	}

	public List<Order> getAllOrders() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Order> query = currentSession.createQuery("from Order", Order.class);
		List<Order> list = query.getResultList();
		return list;
	}

	@Transactional
	public void updateStatus(Order order) {
		entityManager.merge(order);
	}

	public Optional<Order> findByOrderId(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Order order = currentSession.createQuery("FROM Order WHERE id = :id", Order.class).setParameter("id", id)
				.uniqueResult();
		return Optional.ofNullable(order);
	}

	public void deleteOrderById(int orderId) {
		entityManager.createQuery("DELETE FROM Order o WHERE o.id = :orderId").setParameter("orderId", orderId)
				.executeUpdate();
	}

}
