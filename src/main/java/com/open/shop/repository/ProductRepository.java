package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.entity.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

}
