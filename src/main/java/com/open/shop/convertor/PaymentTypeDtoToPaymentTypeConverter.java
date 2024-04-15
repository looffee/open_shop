package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.PaymentTypeDto;
import com.open.shop.model.db.PaymentType;

@Service
public class PaymentTypeDtoToPaymentTypeConverter implements Converter<PaymentTypeDto, PaymentType> {

  public PaymentType convert(@NonNull PaymentTypeDto source) {
    return new PaymentType(
        source.id(),
        source.name(),
        source.description());
  }

}
