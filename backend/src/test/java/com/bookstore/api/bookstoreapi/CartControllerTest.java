package com.bookstore.api.bookstoreapi;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bookstore.api.bookstoreapi.config.security.JWTUtil;
import com.bookstore.api.bookstoreapi.controller.CartController;
import com.bookstore.api.bookstoreapi.model.User;
import com.bookstore.api.bookstoreapi.model.dto.CartDto;
import com.bookstore.api.bookstoreapi.model.dto.CartItemDto;
import com.bookstore.api.bookstoreapi.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

	@MockBean
    private CartService cartService;
	
	@Autowired
    private MockMvc mockMvc;
 
	@Autowired
	private JWTUtil jwtUtil;

    @Autowired
    private Environment env;
   
    
    private String accessToken;

    private CartItemDto cartItemDto;
    private List<CartItemDto> cartItemList;
    private CartDto cartDto;

    @BeforeEach
    void setUp() {
	    User user = new User();
        user.setName("admin");
        user.setEmail(env.getProperty("sa@email.com"));
        user.setPassword(env.getProperty("123456"));
        accessToken = jwtUtil.generateToken(user);
        System.out.println(accessToken);
    	
        cartItemDto = new CartItemDto();
        cartItemDto.setBookId(1L);
        cartItemDto.setQuantity(3);

        cartItemList = new ArrayList<>();
        cartItemList.add(cartItemDto);

        cartDto = new CartDto();
        cartDto.setItems(cartItemList);
    }

    @Test
    @DisplayName("GET /api/v1/cart/myCart - Success")
    void testGetMyCart() throws Exception {
        // Arrange
        doReturn(ResponseEntity.ok().build()).when(cartService).getMyCart();

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/cart/myCart")
        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/v1/cart/addItem - Add Item to Cart")
    void testAddItemToCart() throws Exception {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setBookId(3L);
        cartItemDto.setQuantity(1);

        doReturn(ResponseEntity.ok().build()).when(cartService).addItemToCart(any(CartItemDto.class));

        mockMvc.perform(post("/api/v1/cart/addItem")
        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType("application/json")
                .content(asJsonString(cartItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/v1/cart/removeItem - Remove Item from Cart")
    void testRemoveItemFromCart() throws Exception {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setBookId(1L);
        cartItemDto.setQuantity(0);


        doReturn(ResponseEntity.ok().build()).when(cartService).removeItemFromCart(any(CartItemDto.class));

        mockMvc.perform(post("/api/v1/cart/removeItem")
        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType("application/json")
                .content(asJsonString(cartItemDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/v1/cart/clearCart - Clear Cart")
    void testClearCart() throws Exception {
        doReturn(ResponseEntity.ok().build()).when(cartService).clearCart();

        mockMvc.perform(get("/api/v1/cart/clearCart")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}