package com.gift.controller;



import com.gift.entity.User;    
import com.gift.service.UserService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:3000/")  
@RestController
public class UserController {    

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }
    
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }
    
    
    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId) {
        try {
            userService.deleteByUserId(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }   
    
    
    @GetMapping("/getuser/{id}")
	public User getUserById(@PathVariable("id") Integer userId) {
		User Object = userService.getByUserId(userId);
		if (Object == null) {
			throw new RuntimeException("Employee with id" + userId + "is not found");
		}
		return Object;
	}      
    
    
    @PutMapping("updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User updatedUser) {
        if (updatedUser.getUserId() != userId) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("User ID in the path does not match the ID in the request body.");
        }
        User savedUser = userService.updateUser(updatedUser);
        return ResponseEntity.ok(savedUser);
    }

    
}
