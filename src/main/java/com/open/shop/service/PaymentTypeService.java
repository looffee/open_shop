package com.open.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.PaymentTypeDto;
import com.open.shop.model.db.PaymentType;
import com.open.shop.repository.PaymentTypeRepository;

import reactor.core.publisher.Mono;

@Service
public class PaymentTypeService {

  @Autowired
  @NonNull
  PaymentTypeRepository paymentTypeRepository;

  @Autowired
  @NonNull
  ConversionService conversionService;

  public Mono<PaymentTypeDto> createPaymentType(PaymentTypeDto paymentTypeDto) {
    PaymentType paymentType = conversionService.convert(paymentTypeDto, PaymentType.class);

    if (paymentType == null) {
      throw new IllegalArgumentException("Failed to convert PaymentTypeDto to PaymentType.");
    }

    paymentType = paymentType.toBuilder()
        .id(null)
        .build();

    return paymentTypeRepository.save(paymentType)
        .map(paymentTypeSaved -> conversionService.convert(paymentTypeSaved, PaymentTypeDto.class));
  }

  public Mono<List<PaymentTypeDto>> getAllPaymentTypes() {
    return paymentTypeRepository.findAll()
        .map(paymentType -> conversionService.convert(paymentType, PaymentTypeDto.class))
        .collectList();
  }

  public Mono<Void> deletePaymentType(Long id) {
    return paymentTypeRepository.deleteById(id);
  }

  public Mono<PaymentTypeDto> updatePaymentType(PaymentTypeDto paymentTypeDto) {
    PaymentType paymentType = conversionService.convert(paymentTypeDto, PaymentType.class);

    if (paymentType == null) {
      throw new IllegalArgumentException("Failed to convert PaymentTypeDto to PaymentType.");
    }

    return paymentTypeRepository.save(paymentType)
        .map(paymentTypeSaved -> conversionService.convert(paymentTypeSaved, PaymentTypeDto.class));
  }

}
