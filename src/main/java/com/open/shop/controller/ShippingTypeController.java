package com.open.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.model.db.ShippingType;
import com.open.shop.service.ShippingTypeService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/shipping-type")
public class ShippingTypeController {

  @Autowired
  @NonNull
  ShippingTypeService shippingTypeService;

  @PostMapping("/create")
  public Mono<ShippingType> createShippingType(@RequestBody ShippingType shippingType) {
    return shippingTypeService.createShippingType(shippingType);
  }

}
