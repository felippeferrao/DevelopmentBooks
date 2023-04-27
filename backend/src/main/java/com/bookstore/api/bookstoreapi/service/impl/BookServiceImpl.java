package com.bookstore.api.bookstoreapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstoreapi.converter.BookConverter;
import com.bookstore.api.bookstoreapi.model.Book;
import com.bookstore.api.bookstoreapi.model.dto.BookDto;
import com.bookstore.api.bookstoreapi.repository.BookRepository;
import com.bookstore.api.bookstoreapi.service.BookService;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookConverter bookConverter;

  
    @Override
    public List<BookDto> getAllBook() {
        return bookConverter.entityToDto(bookRepository.findAll());
    }
    
    @Override
    public ResponseEntity<BookDto> getBook(Long id) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            BookDto bookDto = bookConverter.entityToDto(bookData.get());
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
    @Override
    public ResponseEntity<BookDto> createBook(BookDto bookDto) {
        try {
            Book _book = bookRepository.save(bookConverter.dtoToEntity(bookDto));
            return new ResponseEntity<>(bookConverter.entityToDto(_book), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BookDto> updateBook(Long id, BookDto bookDto) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setTitle(bookDto.getTitle());
            return new ResponseEntity<>(bookConverter.entityToDto(bookRepository.save(_book)), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

  
}