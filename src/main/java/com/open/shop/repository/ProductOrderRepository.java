package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.model.db.ProductOrder;

public interface ProductOrderRepository extends ReactiveCrudRepository<ProductOrder, Long> {

}
