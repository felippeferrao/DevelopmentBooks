package com.bookstore.api.bookstoreapi.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	private Integer numBooks;

	@NotNull
	private BigDecimal discount;

}
