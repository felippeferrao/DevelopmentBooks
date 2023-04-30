package com.bookstore.api.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstoreapi.model.Cart;
import com.bookstore.api.bookstoreapi.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
 
	Cart findFirstByCartUserAndStatusOrderByIdDesc(User user, String status);
}
