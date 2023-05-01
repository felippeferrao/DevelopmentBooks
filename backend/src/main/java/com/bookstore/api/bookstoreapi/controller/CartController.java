package com.bookstore.api.bookstoreapi.controller;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstoreapi.model.dto.CartDto;
import com.bookstore.api.bookstoreapi.model.dto.CartItemDto;
import com.bookstore.api.bookstoreapi.service.CartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/cart")
@Api(tags = "Cart Controller", value = "Book Store REST API")
public class CartController implements Serializable {

	
	@Autowired
	private CartService cartService;

	@GetMapping("/myCart")
	@ApiOperation(value = "Get the user's cart")
	@PermitAll
	public ResponseEntity<CartDto> getMyCart() {
	    return cartService.getMyCart();
	}
		
	@PostMapping("/addItem")
	@ApiOperation(value = "Add an item to a cart")
	@PermitAll
	public ResponseEntity<?> addItemToCart(@Valid @RequestBody CartItemDto cartItemDto) {
	    return cartService.addItemToCart(cartItemDto);
	}
	
	@PostMapping("/removeItem")
	@ApiOperation(value = "Remove an item from a cart")
	@PermitAll
	public ResponseEntity<?> removeItemFromCart(@Valid @RequestBody CartItemDto cartItemDto) {
	    return cartService.removeItemFromCart(cartItemDto);
	}
	
	@GetMapping("/clearCart")
	@ApiOperation(value = "Remove all the items from a cart")
	@PermitAll
	public ResponseEntity<?> clearCart() {
	    return cartService.clearCart();
	}
	
}
