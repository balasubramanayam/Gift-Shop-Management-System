package com.gift;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.gift.entity.Cart;
import com.gift.entity.Order;
import com.gift.repository.CartRepository;
import com.gift.repository.OrderRepository;
import com.gift.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@Mock
	private CartRepository cartRepository;

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderService orderService;

	private Order order;
	private Cart cart;

	@Before
	public void setUp() {
		cart = new Cart();
		cart.setId(1);

		order = new Order();
		order.setId(1);
		order.setCart(cart);
		order.setOrderDate(new Date());
		order.setStatus("Pending");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddToOrderNullCart() {
		Order orderRequest = new Order();
		orderRequest.setOrderDate(new Date());
		orderRequest.setStatus("Pending");

		orderService.addToOrder(orderRequest);
	}

	@Test
	public void testGetAllOrders() {
		List<Order> orderList = new ArrayList<>();
		orderList.add(order);

		when(orderRepository.getAllOrders()).thenReturn(orderList);

		List<Order> result = orderService.getAllOrders();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(order, result.get(0));
	}

	@Test
    public void testUpdateOrderStatus() {
        when(orderRepository.findByOrderId(eq(1))).thenReturn(Optional.of(order));

        boolean result = orderService.updateOrderStatus(1, "Completed");

        assertTrue(result);
        assertEquals("Completed", order.getStatus());
        verify(orderRepository, times(1)).updateStatus(eq(order));
    }

	@Test
    public void testUpdateOrderStatusNotFound() {
        when(orderRepository.findByOrderId(eq(1))).thenReturn(Optional.empty());

        boolean result = orderService.updateOrderStatus(1, "Completed");

        assertFalse(result);
        verify(orderRepository, never()).updateStatus(any());
    }

	@Test
    public void testGetOrderById() {
        when(orderRepository.findByOrderId(eq(1))).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(1);

        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }

	@Test
    public void testGetOrderByIdNotFound() {
        when(orderRepository.findByOrderId(eq(1))).thenReturn(Optional.empty());

        Optional<Order> result = orderService.getOrderById(1);

        assertFalse(result.isPresent());
    }

	@Test
	public void testDeleteOrder() {
		orderService.deleteOrder(1);

		verify(orderRepository, times(1)).deleteOrderById(eq(1));
	}
}
