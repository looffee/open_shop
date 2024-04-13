package com.open.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.model.CreateProductOrder;
import com.open.shop.model.db.ProductOrder;
import com.open.shop.service.ProductOrderService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product-order")
public class ProductOrderController {

  @Autowired
  @NonNull
  ProductOrderService productOrderService;

  @PostMapping("/create")
  public Mono<ProductOrder> createProductOrder(@RequestBody CreateProductOrder createProductOrder) {
    return productOrderService.createProductOrder(createProductOrder);
  }

}
