package com.gift;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.gift.entity.Cart;
import com.gift.entity.GiftList;
import com.gift.entity.User;
import com.gift.repository.CartRepository;
import com.gift.repository.GiftListRepository;
import com.gift.repository.UserRepository;
import com.gift.service.GiftListService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class GiftListServiceTest {

	@Mock
	private GiftListRepository giftListRepository;

	@Mock
	private CartRepository cartRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private GiftListService giftListService;

	private GiftList gift;
	private User user;

	@Before
	public void setUp() {
		gift = new GiftList();
		gift.setId(1);
		gift.setName("Test Gift");
		gift.setPrice(20);
		gift.setDescription("Test Description");
		user = new User();
		user.setUserId(1);
		user.setEmail("test@example.com");
	}

	@Test
	public void testSaveGiftWithImage() throws IOException {
		MultipartFile imageFile = new MockMultipartFile("test.jpg", new byte[] { 0x12, 0x34 });
		giftListService.saveGiftWithImage("Test Gift", 20, "Test Description", imageFile);

		verify(giftListRepository, times(1)).saveGiftWithImage(any());
	}

	@Test
	public void testGetAllGifts() {
		List<GiftList> giftList = new ArrayList<>();
		giftList.add(gift);

		when(giftListRepository.getAllGifts()).thenReturn(giftList);

		List<GiftList> result = giftListService.getAllGifts();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(gift, result.get(0));
	}

	@Test
    public void testGetImageById() {
        when(giftListRepository.getImageById(eq(1))).thenReturn(gift);

        GiftList result = giftListService.getImageById(1);

        assertNotNull(result);
        assertEquals(gift, result);
    }

	@Test
	public void testDeleteImage() {
		giftListService.deleteImage(1);

		verify(giftListRepository, times(1)).deleteImage(eq(1));
	}

}
