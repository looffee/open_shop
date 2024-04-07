package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.entity.ShipmentType;

public interface ShipmentTypeRepository extends ReactiveCrudRepository<ShipmentType, Long> {

}
