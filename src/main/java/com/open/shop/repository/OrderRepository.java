package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.entity.Order;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {

}
