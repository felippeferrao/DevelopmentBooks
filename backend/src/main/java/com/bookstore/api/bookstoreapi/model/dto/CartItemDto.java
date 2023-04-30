package com.bookstore.api.bookstoreapi.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long cartId;
	
    @NotNull(message = "Book ID is mandatory")
    private Long bookId;
    
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;
}