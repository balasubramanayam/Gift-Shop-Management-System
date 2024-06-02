package com.gift;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.gift.entity.Cart;
import com.gift.repository.CartRepository;
import com.gift.repository.GiftListRepository;
import com.gift.service.CartService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private GiftListRepository giftListRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
        cart.setId(1);
        cart.setQuantity(1);
        cart.setTotalPrice(20);
        cart.setTotalGifts(1);
    }

    @Test
    public void testGetAll() {
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        when(cartRepository.getAll()).thenReturn(cartList);

        List<Cart> result = cartService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cart, result.get(0));
    }

    @Test
    public void testRemoveCart() {
        cartService.removeCart(1);

        verify(cartRepository, times(1)).deleteById(eq(1));
    }

   

    @Test
    public void testGetById() {
        when(cartRepository.findById(eq(1))).thenReturn(cart);

        Cart result = cartService.getById(1);

        assertNotNull(result);
        assertEquals(cart, result);
    }

    @Test
    public void testDeleteCart() {
        cartService.deleteCart(1);

        verify(cartRepository, times(1)).deleteById(eq(1));
    }
}
