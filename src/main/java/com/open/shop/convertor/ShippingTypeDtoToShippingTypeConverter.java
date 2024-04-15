package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.ShippingTypeDto;
import com.open.shop.model.db.ShippingType;

@Service
public class ShippingTypeDtoToShippingTypeConverter implements Converter<ShippingTypeDto, ShippingType> {

  public ShippingType convert(@NonNull ShippingTypeDto source) {
    return new ShippingType(
        source.id(),
        source.name(),
        source.price(),
        source.description());
  }

}
