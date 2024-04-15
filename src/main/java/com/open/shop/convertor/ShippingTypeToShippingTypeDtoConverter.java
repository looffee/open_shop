package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.ShippingTypeDto;
import com.open.shop.model.db.ShippingType;

@Service
public class ShippingTypeToShippingTypeDtoConverter implements Converter<ShippingType, ShippingTypeDto> {

  public ShippingTypeDto convert(@NonNull ShippingType source) {
    return new ShippingTypeDto(
        source.id(),
        source.name(),
        source.price(),
        source.description());
  }
}
