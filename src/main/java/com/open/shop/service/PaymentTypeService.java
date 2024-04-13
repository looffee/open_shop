package com.open.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.db.PaymentType;
import com.open.shop.repository.PaymentTypeRepository;

import reactor.core.publisher.Mono;

@Service
public class PaymentTypeService {

  @Autowired
  @NonNull
  PaymentTypeRepository paymentTypeRepository;

  public Mono<PaymentType> createPaymentType(PaymentType paymentType) {
    return paymentTypeRepository.save(
        PaymentType.builder()
            .id(null)
            .name(paymentType.name())
            .description(paymentType.description())
            .build());
  }

}
