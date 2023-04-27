package com.bookstore.api.bookstoreapi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
@Table(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Book ID", example = "1", required = true, hidden = true)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Title is mandatory")
    @ApiModelProperty(notes = "Title", example = "Book One", required = true)
    private String title;

    @Column(name = "author")
    @NotBlank(message = "Author is mandatory")
    @ApiModelProperty(notes = "Author", example = "Author book One", required = true)
    private String author;
    
    @Column(name = "year_book")
    @NotNull(message = "Year is mandatory")
    @ApiModelProperty(notes = "Year", example = "2023", required = true)
    private Integer year;
    
    @Column(name = "description", length = 1000)
    @NotBlank(message = "Description is mandatory")
    @ApiModelProperty(notes = "Description", example = "Book One is a best seller.", required = true)
    private String description;
    
    @Column(name = "image_url", length = 1000)
    @NotBlank(message = "Image URL is mandatory")
    @ApiModelProperty(notes = "Image Url", example = "https://imagebanks/imagebookone.jpg", required = true)
    private String imageUrl;    
    
    @Column(name = "price")
    @NotNull(message = "Price is mandatory")
    @ApiModelProperty(notes = "Price", example = "50", required = true)
    private BigDecimal price;
    
}