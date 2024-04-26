package com.open.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.model.api.CreateProductOrderRequest;
import com.open.shop.model.api.ProductOrderDto;
import com.open.shop.service.ProductOrderService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product-order")
public class ProductOrderController {

  @Autowired
  @NonNull
  ProductOrderService productOrderService;

  Logger logger = LoggerFactory.getLogger(ProductOrderController.class);

  @Autowired
  @NonNull
  Validator validator;

  @Operation(summary = "Create a product order")
  @PostMapping("/create")
  public Mono<ProductOrderDto> createProductOrder(@Valid @RequestBody CreateProductOrderRequest requestBody) {
    return productOrderService.createProductOrder(requestBody);
  }

}
