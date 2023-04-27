package com.bookstore.api.bookstoreapi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemKey implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cartId;

    private Long bookId;

}