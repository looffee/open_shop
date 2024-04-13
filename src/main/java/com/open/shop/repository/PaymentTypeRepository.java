package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.model.db.PaymentType;

public interface PaymentTypeRepository extends ReactiveCrudRepository<PaymentType, Long> {

}
