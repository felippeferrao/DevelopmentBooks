package com.bookstore.api.bookstoreapi.service;

import org.springframework.http.ResponseEntity;

import com.bookstore.api.bookstoreapi.model.dto.CartDto;
import com.bookstore.api.bookstoreapi.model.dto.CartItemDto;

public interface CartService {
	
	ResponseEntity<CartDto> getMyCart();
	ResponseEntity<CartDto> addItemToCart(CartItemDto cartItemDto);
	ResponseEntity<CartDto> removeItemFromCart(CartItemDto cartItemDto);
	ResponseEntity<CartDto> clearCart();
	ResponseEntity<CartDto> checkoutCart();
}
