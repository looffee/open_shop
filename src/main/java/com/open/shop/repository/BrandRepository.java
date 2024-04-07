package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.entity.Brand;

public interface BrandRepository extends ReactiveCrudRepository<Brand, Long> {

}
