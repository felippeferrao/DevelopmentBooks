package com.bookstore.api.bookstoreapi;

import javax.annotation.security.PermitAll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/home")
@RestController
public class BookstoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApiApplication.class, args);
	}

	@GetMapping("/index")
	@PermitAll
	public String index() {
		return "Home Api Requested";
	}
	
	@GetMapping("/unauthorized")
	public String indexUnauthorized() {
		return "Unauthorized Content";
	}
}
