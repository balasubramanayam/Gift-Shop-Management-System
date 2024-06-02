package com.gift;


import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gift.controller.AdminController;

class AdminControllerTest {

    private AdminController adminController;

    @BeforeEach
    void setUp() {
        adminController = new AdminController();
    }

    @Test
    void testPerformLogin_Successful() {
       
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", "admin@gmail.com");
        loginRequest.put("password", "1234");

       
        ResponseEntity<String> response = adminController.performLogin(loginRequest);

      
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful", response.getBody());
    }

    @Test
    void testPerformLogin_Unsuccessful() {
     
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", "admin@gmail.com");
        loginRequest.put("password", "wrongpassword");

     
        ResponseEntity<String> response = adminController.performLogin(loginRequest);

      
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password", response.getBody());
    }
}    

