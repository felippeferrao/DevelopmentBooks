package com.bookstore.api.bookstoreapi.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bookstore.api.bookstoreapi.model.Book;
import com.bookstore.api.bookstoreapi.model.dto.BookDto;

@Component
public class BookConverter {

    
    public BookDto entityToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setYear(book.getYear());
        bookDto.setDescription(book.getDescription());
        bookDto.setImageUrl(book.getImageUrl());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }

    public List<BookDto> entityToDto(List<Book> book) {
        return book.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Book dtoToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setYear(bookDto.getYear());
        book.setDescription(bookDto.getDescription());
        book.setImageUrl(bookDto.getImageUrl());
        book.setPrice(bookDto.getPrice());
        
        return book;
    }

    public List<Book> dtoToEntity(List<BookDto> bookDtos) {
        return bookDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
