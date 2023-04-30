package com.bookstore.api.bookstoreapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "cart_items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CartItemKey id;

    @Column(name = "quantity")
    @NotNull(message = "Quantity is mandatory")
    @ApiModelProperty(notes = "Quantity", example = "1", required = true)
    private Integer quantity;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private Cart cart;
}