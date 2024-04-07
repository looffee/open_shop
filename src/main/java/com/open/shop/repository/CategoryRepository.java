package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.entity.Category;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {

}