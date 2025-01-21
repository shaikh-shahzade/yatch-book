package com.shah.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shah.App.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
