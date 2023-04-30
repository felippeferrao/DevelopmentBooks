package com.bookstore.api.bookstoreapi.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String token;
	private String name;
	
}
