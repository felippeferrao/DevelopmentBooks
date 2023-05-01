package com.bookstore.api.bookstoreapi.model.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetBooksDto {
    private BigDecimal totalPrice;
    private BigDecimal totalDiscount;
    private List<List<SetDto>> bookSets;

}