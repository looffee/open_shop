package com.open.shop.model.db;

import org.springframework.data.annotation.Id;

import lombok.Builder;

@Builder
public record ProductOrderItem(
    @Id Long id,
    long productOrderId,
    long productId,
    int quantity,
    double price,
    double discount) {

  public double total() {
    return (price - discount) * quantity;
  }
}
