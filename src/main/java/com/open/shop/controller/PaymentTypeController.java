package com.open.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.model.db.PaymentType;
import com.open.shop.service.PaymentTypeService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/payment-type")
public class PaymentTypeController {

  @Autowired
  @NonNull
  PaymentTypeService paymentTypeService;

  @PostMapping("/create")
  public Mono<PaymentType> createPaymentType(@RequestBody PaymentType paymentType) {
    return paymentTypeService.createPaymentType(paymentType);
  }

}
