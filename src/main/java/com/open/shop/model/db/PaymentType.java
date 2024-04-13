package com.open.shop.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import lombok.Builder;

@Builder
public record PaymentType(
    @Id Long id,
    @NonNull String name,
    String description) {

}
