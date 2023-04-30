package com.bookstore.api.bookstoreapi.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.api.bookstoreapi.model.CartItem;
import com.bookstore.api.bookstoreapi.model.CartItemKey;
import com.bookstore.api.bookstoreapi.model.dto.CartItemDto;

@Component
public class CartItemConverter {

    @Autowired
    private BookConverter bookConverter;

    public CartItem dtoToEntity(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        CartItemKey cartItemKey = new CartItemKey();
        cartItemKey.setCartId(cartItemDto.getCartId());
        cartItemKey.setBookId(cartItemDto.getBookId());
        cartItem.setId(cartItemKey);
        cartItem.setQuantity(cartItemDto.getQuantity());
        return cartItem;
    }

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCartId(cartItem.getId().getCartId());
        cartItemDto.setBookId(cartItem.getId().getBookId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setBookId((bookConverter.entityToDto(cartItem.getBook())).getId());
        return cartItemDto;
    }

    public List<CartItemDto> entityToDto(List<CartItem> cartItems) {
        return cartItems.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
