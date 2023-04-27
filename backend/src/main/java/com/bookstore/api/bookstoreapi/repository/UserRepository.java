package com.bookstore.api.bookstoreapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.api.bookstoreapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
