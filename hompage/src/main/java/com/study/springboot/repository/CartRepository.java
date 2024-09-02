package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springboot.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findByMemIdOrderById(String memId);

}
