package com.open.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.db.ShippingType;
import com.open.shop.repository.ShippingTypeRepository;

import reactor.core.publisher.Mono;

@Service
public class ShippingTypeService {

  @Autowired
  @NonNull
  ShippingTypeRepository shippingTypeRepository;

  public Mono<ShippingType> createShippingType(ShippingType shippingType) {
    return shippingTypeRepository.save(
        ShippingType.builder()
            .id(null)
            .name(shippingType.name())
            .price(shippingType.price())
            .description(shippingType.description())
            .build());
  }

}
