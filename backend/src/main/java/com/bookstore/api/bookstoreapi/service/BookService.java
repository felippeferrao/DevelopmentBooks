package com.bookstore.api.bookstoreapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bookstore.api.bookstoreapi.model.dto.BookDto;

public interface BookService {

    List<BookDto> getAllBook();
    ResponseEntity<BookDto> getBook(Long id); 
    ResponseEntity<BookDto> createBook(BookDto bookDto);
    ResponseEntity<BookDto> updateBook(Long id, BookDto bookDto);
}