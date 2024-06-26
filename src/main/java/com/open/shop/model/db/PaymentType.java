package com.open.shop.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import lombok.Builder;

@Builder
public record PaymentType(
    @Id Long id,
    @NonNull String name,
    @NonNull String description) {

  public PaymentTypeBuilder toBuilder() {
    return new PaymentTypeBuilder()
        .id(id)
        .name(name)
        .description(description);
  }

}
