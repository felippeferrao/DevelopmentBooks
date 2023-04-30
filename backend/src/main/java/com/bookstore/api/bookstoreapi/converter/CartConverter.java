package com.bookstore.api.bookstoreapi.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.api.bookstoreapi.model.Cart;
import com.bookstore.api.bookstoreapi.model.CartItem;
import com.bookstore.api.bookstoreapi.model.dto.CartDto;
import com.bookstore.api.bookstoreapi.model.dto.CartItemDto;
import com.bookstore.api.bookstoreapi.model.dto.SetBooksDto;

@Component
public class CartConverter {
    
    @Autowired
    private BookConverter bookConverter;
    
    public CartDto entityToDto(Cart cart, SetBooksDto discountSet) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setStatus(cart.getStatus());
        //cartDto.setCartUser(cart.getCartUser());
        
        if (cart.getCartItems() != null) {
            List<CartItemDto> cartItemsDto = cart.getCartItems().stream()
                    .map(cartItem -> entityToDto(cartItem)).collect(Collectors.toList());
            cartDto.setItems(cartItemsDto);
        }
        
        cartDto.setDiscountSet(discountSet);
        
        return cartDto;
    }

    public Cart dtoToEntity(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setStatus(cartDto.getStatus());
        cart.setCartUser(cartDto.getCartUser());
        
        List<CartItem> cartItems = new ArrayList<>();
        if (cartDto.getItems() != null) {
            cartItems = cartDto.getItems().stream().map(cartItemDto -> dtoToEntity(cartItemDto))
                .collect(Collectors.toList());
        }
        cart.setCartItems(cartItems);
        return cart;
    }

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setBookId((bookConverter.entityToDto(cartItem.getBook()).getId()));
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }

    public CartItem dtoToEntity(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        //cartItem.setBook(bookConverter.dtoToEntity(cartItemDto.getBook()));
        cartItem.setQuantity(cartItemDto.getQuantity());
        return cartItem;
    }
}
