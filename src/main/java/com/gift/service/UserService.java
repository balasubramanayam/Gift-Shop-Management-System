package com.gift.service;



import com.gift.entity.User;
import com.gift.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.registerUser(user);
    }

    public User login(User user) {
		User existingUser = userRepository.findByEmail(user.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + user.getEmail()));

		if (!user.getPassword().equals(existingUser.getPassword())) {
			throw new RuntimeException("Password is incorrect");
		}

		return existingUser;
	}
    
    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void deleteByUserId(Integer userId) {
        userRepository.deleteByUserId(userId);
    }
        
   
	public User getByUserId(int userId) {
		
		return userRepository.getByUserId(userId);
	}
   
	
	@Transactional
	public User updateUser(User updatedUser) {
	    return userRepository.save(updatedUser);
	}

}

