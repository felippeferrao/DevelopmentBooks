package com.bookstore.api.bookstoreapi.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable {
    private Long id;
    
    @NotNull
    @NotBlank(message = "Title is mandatory")
    private String title;
    
    @NotNull
    @NotBlank(message = "Author is mandatory")
    private String author;
    
    @NotNull(message = "Year is mandatory")
    private Integer year;
    
    @NotNull
    @NotBlank(message = "Description is mandatory")
    private String description;
    
    @NotNull
    @NotBlank(message = "Image URL is mandatory")
    private String imageUrl;
    
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;
}