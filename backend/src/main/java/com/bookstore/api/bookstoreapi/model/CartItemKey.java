package com.bookstore.api.bookstoreapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartItemKey implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "book_id")
    private Long bookId;
}