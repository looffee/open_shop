package com.open.shop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public record ShippingType(
    @Id Long id,
    @NonNull String name,
    double price,
    String description) {
}
