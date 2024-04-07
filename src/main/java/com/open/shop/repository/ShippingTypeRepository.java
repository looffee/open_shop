package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.entity.ShippingType;

public interface ShippingTypeRepository extends ReactiveCrudRepository<ShippingType, Long> {

}
