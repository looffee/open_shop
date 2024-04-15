package com.open.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.ShippingTypeDto;
import com.open.shop.model.db.ShippingType;
import com.open.shop.repository.ShippingTypeRepository;

import reactor.core.publisher.Mono;

@Service
public class ShippingTypeService {

  @Autowired
  @NonNull
  ShippingTypeRepository shippingTypeRepository;

  @Autowired
  @NonNull
  ConversionService conversionService;

  public Mono<ShippingTypeDto> createShippingType(ShippingTypeDto shippingTypeDto) {
    ShippingType shippingType = conversionService.convert(shippingTypeDto, ShippingType.class);

    if (shippingType == null) {
      throw new IllegalArgumentException("Failed to convert ShippingTypeDto to ShippingType.");
    }

    shippingType = shippingType.toBuilder()
        .id(null)
        .build();

    return shippingTypeRepository.save(shippingType)
        .map(saved -> conversionService.convert(saved, ShippingTypeDto.class));
  }

  public Mono<List<ShippingTypeDto>> getAllShippingTypes() {
    return shippingTypeRepository
        .findAll()
        .map(shippingType -> {
          return conversionService.convert(shippingType, ShippingTypeDto.class);
        })
        .collectList();
  }

}
