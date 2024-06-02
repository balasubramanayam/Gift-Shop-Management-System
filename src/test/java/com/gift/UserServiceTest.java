package com.gift;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.gift.entity.User;
import com.gift.repository.UserRepository;
import com.gift.service.UserService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setUserId(1);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        // Set up mock behavior for userRepository methods
        when(userRepository.findByEmail(eq("test@example.com"))).thenReturn(Optional.of(user));
        when(userRepository.getAll()).thenReturn(new ArrayList<>());
        when(userRepository.getByUserId(eq(1))).thenReturn(user);
    }

    @Test
    public void testRegisterUser() {
        User newUser = new User();
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("newpassword123");

        userService.registerUser(newUser);

        verify(userRepository, times(1)).registerUser(eq(newUser));
    }

    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setEmail("test@example.com");
        loginUser.setPassword("password123");

        User loggedInUser = userService.login(loginUser);

        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
    }

    @Test
    public void testGetAll() {
        List<User> allUsers = userService.getAll();

        assertNotNull(allUsers);
        assertEquals(0, allUsers.size());
    }

    @Test
    public void testDeleteByUserId() {
        userService.deleteByUserId(1);

        verify(userRepository, times(1)).deleteByUserId(eq(1));
    }

    @Test
    public void testGetByUserId() {
        User fetchedUser = userService.getByUserId(1);

        assertNotNull(fetchedUser);
        assertEquals(user, fetchedUser);
    }

    @Test
    public void testUpdateUser() {   
        User updatedUser = new User();
        updatedUser.setUserId(1);
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("updatedpassword123");

        when(userRepository.save(eq(updatedUser))).thenReturn(updatedUser);

        User returnedUser = userService.updateUser(updatedUser);

        assertNotNull(returnedUser);
        assertEquals(updatedUser, returnedUser);
    }
}
