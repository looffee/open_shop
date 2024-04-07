package com.open.shop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public record ShipmentType(
    @Id Long id,
    @NonNull String name,
    String description) {
}
