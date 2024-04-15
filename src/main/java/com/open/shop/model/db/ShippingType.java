package com.open.shop.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import lombok.Builder;

@Builder
public record ShippingType(
    @Id Long id,
    @NonNull String name,
    double price,
    @NonNull String description) {

  public ShippingTypeBuilder toBuilder() {
    return new ShippingTypeBuilder()
        .id(id)
        .name(name)
        .price(price)
        .description(description);
  }
}
