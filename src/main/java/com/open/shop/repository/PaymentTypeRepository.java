package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.entity.PaymentType;

public interface PaymentTypeRepository extends ReactiveCrudRepository<PaymentType, Long> {

}
