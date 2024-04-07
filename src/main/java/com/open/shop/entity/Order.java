package com.open.shop.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Builder;

@Builder
public record Order(
    @Id Long id,
    long userId,
    long userAddressId,
    long shippingTypeId,
    ShippingStatus shippingStatus,
    long paymentTypeId,
    PaymentStatus paymentStatus,
    OrderStatus orderStatus,
    String userNotes,
    @CreatedDate LocalDateTime createdAt,
    @LastModifiedDate LocalDateTime updatedAt) {

  public OrderBuilder builder() {
    return new OrderBuilder()
        .id(id)
        .userId(userId)
        .userAddressId(userAddressId)
        .shippingTypeId(shippingTypeId)
        .shippingStatus(shippingStatus)
        .paymentTypeId(paymentTypeId)
        .paymentStatus(paymentStatus)
        .orderStatus(orderStatus)
        .userNotes(userNotes)
        .createdAt(createdAt)
        .updatedAt(updatedAt);
  }
}
