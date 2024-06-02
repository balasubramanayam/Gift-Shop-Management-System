package com.gift.controller;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000/")
@RestController
public class AdminController {

		@PostMapping("/admin")
		public ResponseEntity<String> performLogin(@RequestBody Map<String, String> loginRequest) {
			String email = loginRequest.get("email");
			String password = loginRequest.get("password");

			if (email.equals("admin@gmail.com") && password.equals("1234")) {  
				return ResponseEntity.ok("Login successful");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
			}
		}
	}

