package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.PaymentTypeDto;
import com.open.shop.model.db.PaymentType;

@Service
public class PaymentTypeToPaymentTypeDtoConverter implements Converter<PaymentType, PaymentTypeDto> {

  public PaymentTypeDto convert(@NonNull PaymentType source) {
    return new PaymentTypeDto(
        source.id(),
        source.name(),
        source.description());
  }

}
