package com.bookstore.api.bookstoreapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
@IdClass(CartItemKey.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "cart_id")
    @ApiModelProperty(notes = "Cart ID", example = "1", required = true, hidden = true)
    private Long cartId;

    @Id
    @Column(name = "book_id")
    @ApiModelProperty(notes = "Book ID", example = "1", required = true, hidden = true)
    private Long bookId;
    
    @Column(name = "quantity")
    @NotBlank(message = "Quantity is mandatory")
    @ApiModelProperty(notes = "Quantity", example = "1", required = true)
    private Integer quantity;

}