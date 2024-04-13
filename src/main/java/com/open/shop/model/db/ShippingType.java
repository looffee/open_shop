package com.open.shop.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import lombok.Builder;

@Builder
public record ShippingType(
    @Id Long id,
    @NonNull String name,
    double price,
    String description) {
}
