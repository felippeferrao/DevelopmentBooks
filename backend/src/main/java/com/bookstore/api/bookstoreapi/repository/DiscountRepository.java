package com.bookstore.api.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstoreapi.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
