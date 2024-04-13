package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.model.db.ProductOrderItem;

public interface ProductOrderItemRepository extends ReactiveCrudRepository<ProductOrderItem, Long> {

}
