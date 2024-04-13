package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.model.db.ShippingType;

public interface ShippingTypeRepository extends ReactiveCrudRepository<ShippingType, Long> {

}
