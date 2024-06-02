package com.gift.repository;

import com.gift.entity.User;


import jakarta.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

	@Autowired
	private EntityManager entityManager;

	public void registerUser(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);
	}

	public Optional<User> findByEmail(String email) {
		return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}
	
	
	 public List<User> getAll() {
	        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
	    }

	    public void deleteByUserId(Integer userId) {
	        User user = entityManager.find(User.class, userId);
	        if (user != null) {
	            entityManager.remove(user);
	        } else {
	            throw new IllegalArgumentException("User not found with ID: " + userId);
	        }
	    }
	    
	  
	    
		public User getByUserId(int userId) {
			Session currentSession = entityManager.unwrap(Session.class);
			return currentSession.get(User.class, userId);
			
		}
		
		 @Transactional
		    public User save(User user) {
		        if (user.getUserId() == 0) {
		            entityManager.persist(user);
		            return user;
		        } else {
		            return entityManager.merge(user);
		        }
		    }

}
