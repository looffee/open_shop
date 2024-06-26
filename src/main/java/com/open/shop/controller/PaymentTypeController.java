package com.open.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.model.api.PaymentTypeDto;
import com.open.shop.service.PaymentTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotEmpty;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/payment-type")
public class PaymentTypeController {

  @Autowired
  @NonNull
  PaymentTypeService paymentTypeService;

  @Operation(summary = "Create a payment type", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/create")
  public Mono<PaymentTypeDto> createPaymentType(@RequestBody PaymentTypeDto paymentType) {
    return paymentTypeService.createPaymentType(paymentType);
  }

  @Operation(summary = "Delete a payment type by id", security = @SecurityRequirement(name = "bearerAuth"))
  @DeleteMapping("/delete/{id}")
  public Mono<Void> deletePaymentType(@PathVariable("id") @NotEmpty Long id) {
    return paymentTypeService.deletePaymentType(id);
  }

  @Operation(summary = "Update a payment type by id", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/update/{id}")
  public Mono<PaymentTypeDto> updatePaymentType(
      @PathVariable("id") @NotEmpty long id,
      @RequestBody PaymentTypeDto paymentType) {
    return paymentTypeService.updatePaymentType(paymentType);
  }

  @Operation(summary = "Get all payment types")
  @GetMapping("/get-all")
  public Mono<List<PaymentTypeDto>> getAllPaymentTypes() {
    return paymentTypeService.getAllPaymentTypes();
  }

}
