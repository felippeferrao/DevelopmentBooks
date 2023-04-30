package com.bookstore.api.bookstoreapi.model.dto;

import java.io.Serializable;
import java.util.List;

import com.bookstore.api.bookstoreapi.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto implements Serializable {

    private Long id;
    private String status;
    private User cartUser;
    private List<CartItemDto> items;
    private SetBooksDto discountSet;
}
