package com.open.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.model.api.ShippingTypeDto;
import com.open.shop.service.ShippingTypeService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/shipping-type")
public class ShippingTypeController {

  @Autowired
  @NonNull
  ShippingTypeService shippingTypeService;

  @PostMapping("/create")
  public Mono<ShippingTypeDto> createShippingType(@RequestBody ShippingTypeDto shippingType) {
    return shippingTypeService.createShippingType(shippingType);
  }

  @GetMapping("/get-all")
  public Mono<List<ShippingTypeDto>> getAllShippingTypes() {
    return shippingTypeService.getAllShippingTypes();
  }

}
