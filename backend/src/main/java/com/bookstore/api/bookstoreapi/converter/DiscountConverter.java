package com.bookstore.api.bookstoreapi.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bookstore.api.bookstoreapi.model.Discount;
import com.bookstore.api.bookstoreapi.model.dto.DiscountDto;

@Component
public class DiscountConverter {

    public DiscountDto entityToDto(Discount discount) {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setId(discount.getId());
        discountDto.setNumBooks(discount.getNumBooks());
        discountDto.setDiscount(discount.getDiscount());
        return discountDto;
    }

    public List<DiscountDto> entityToDto(List<Discount> discounts) {
        return discounts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Discount dtoToEntity(DiscountDto discountDto) {
        Discount discount = new Discount();
        discount.setId(discountDto.getId());
        discount.setNumBooks(discountDto.getNumBooks());
        discount.setDiscount(discountDto.getDiscount());
        return discount;
    }

    public List<Discount> dtoToEntity(List<DiscountDto> discountDtos) {
        return discountDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
