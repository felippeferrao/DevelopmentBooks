package com.bookstore.api.bookstoreapi.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.bookstore.api.bookstoreapi.model.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private Book book;
    private Integer quantity;
    private BigDecimal discount;
}
