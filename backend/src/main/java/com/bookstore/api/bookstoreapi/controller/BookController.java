package com.bookstore.api.bookstoreapi.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstoreapi.model.Book;
import com.bookstore.api.bookstoreapi.model.dto.BookDto;
import com.bookstore.api.bookstoreapi.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/book")
@Api(tags = "Book Controller", value = "Book Store REST API")
public class BookController implements Serializable {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    @ApiOperation(value = "List all books", response = Book.class)
    @PermitAll
    public List<BookDto> getAllBook() {
        return bookService.getAllBook();
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "List a book by id", response = Book.class)
    @PermitAll
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    @ApiOperation(value = "Add a book")
    @PermitAll
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a book")
    @PermitAll
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

}
