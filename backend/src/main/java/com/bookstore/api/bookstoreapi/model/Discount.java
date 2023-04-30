package com.bookstore.api.bookstoreapi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Discount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Discount ID", example = "1", required = true, hidden = true)
    private Long id;

    @Column(name = "num_books")
    @NotNull(message = "Number of Different Books is mandatory")
    @ApiModelProperty(notes = "Num Books", example = "1", required = true)
    private Integer numBooks;
    
    @Column(name = "discount")
    @NotNull(message = "Dicount is mandatory")
    @ApiModelProperty(notes = "Discount", example = "10", required = true)
    private BigDecimal dicount;
    
}