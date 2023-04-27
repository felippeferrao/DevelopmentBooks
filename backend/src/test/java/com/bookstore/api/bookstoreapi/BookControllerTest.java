package com.bookstore.api.bookstoreapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bookstore.api.bookstoreapi.config.security.JWTUtil;
import com.bookstore.api.bookstoreapi.model.User;
import com.bookstore.api.bookstoreapi.model.dto.BookDto;
import com.bookstore.api.bookstoreapi.service.BookService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Environment env;

    @Autowired
    private JWTUtil jwtUtil;

    private String accessToken;

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setName("admin");
        user.setEmail(env.getProperty("sa@email.com"));
        user.setPassword(env.getProperty("123456"));
        accessToken = jwtUtil.generateToken(user);
    }

    @Test
    @DisplayName("GET /api/v1/book/list - OK")
    void testGetBookList() throws Exception {
    String result = mockMvc.perform(get("/api/v1/book/list")
              .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
             .andExpect(status().isOk())
              .andReturn()
             .getResponse()
             .getContentAsString();
    }
    
    @Test
    @DisplayName("GET /api/v1/book/1 - Found")
    void testGetBookByIdFound() throws Exception {
    String result = mockMvc.perform(get("/api/v1/book/detail/{id}",1L)
              .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
             .andExpect(status().isOk())
              .andReturn()
             .getResponse()
             .getContentAsString();
    }
    
       
    @Test
    @DisplayName("GET /api/v1/book/detail/{id} - Not Found")
    void testGetBookByIdNotFound() throws Exception {
        String result = mockMvc.perform(get("/api/v1/book/detail/{id}", 9999L)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();      
        
        //Empty return means that the id is not present in the DB
        assertEquals("", result);
    }

    
    
    @Test
    @DisplayName("POST /api/v1/book - Success")
    public void testCreateBookSuccess() throws Exception {
        // Arrange
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Book One");
        bookDto.setAuthor("Author One");
        bookDto.setDescription("Description One");
        bookDto.setYear(2022);
        bookDto.setImageUrl("https://imagebooks/bookone.jpg");
        bookDto.setPrice(new BigDecimal("50.0"));

        doReturn(ResponseEntity.ok(bookDto)).when(bookService).createBook(bookDto);

        // Act and Assert
        mockMvc.perform(post("/api/v1/book")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType("application/json")
                .content(asJsonString(bookDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/v1/book/1 - Success")
    public void testUpdateBookSuccess() throws Exception {
        // Arrange
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Book One Updated");
        bookDto.setAuthor("Author One Updated");
        bookDto.setDescription("Description One Updated");
        bookDto.setYear(2023);
        bookDto.setImageUrl("https://imagebooks/bookone-updated.jpg");
        bookDto.setPrice(new BigDecimal("60.0"));

        doReturn(ResponseEntity.ok(bookDto)).when(bookService).updateBook(1L, bookDto);

        // Act and Assert
        mockMvc.perform(put("/api/v1/book/{id}", 1L)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType("application/json")
                .content(asJsonString(bookDto)))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}