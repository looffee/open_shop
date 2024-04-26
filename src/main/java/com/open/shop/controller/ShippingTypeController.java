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

import com.open.shop.model.api.ShippingTypeDto;
import com.open.shop.service.ShippingTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotEmpty;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/shipping-type")
public class ShippingTypeController {

  @Autowired
  @NonNull
  ShippingTypeService shippingTypeService;

  @Operation(summary = "Create a shipping type", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/create")
  public Mono<ShippingTypeDto> createShippingType(@RequestBody ShippingTypeDto shippingType) {
    return shippingTypeService.createShippingType(shippingType);
  }

  @Operation(summary = "Delete a shipping type by id", security = @SecurityRequirement(name = "bearerAuth"))
  @DeleteMapping("/delete/{id}")
  public Mono<Void> deleteShippingType(@PathVariable("id") @NotEmpty Long id) {
    return shippingTypeService.deleteShippingType(id);
  }

  @Operation(summary = "Update a shipping type by id", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/update/{id}")
  public Mono<ShippingTypeDto> updateShippingType(
      @PathVariable("id") @NotEmpty long id,
      @RequestBody ShippingTypeDto shippingType) {
    return shippingTypeService.updateShippingType(shippingType);
  }

  @Operation(summary = "Get all shipping types")
  @GetMapping("/get-all")
  public Mono<List<ShippingTypeDto>> getAllShippingTypes() {
    return shippingTypeService.getAllShippingTypes();
  }

}
