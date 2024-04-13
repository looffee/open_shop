package com.open.shop.model.db;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import com.open.shop.model.OrderStatus;
import com.open.shop.model.PaymentStatus;
import com.open.shop.model.ShippingStatus;

import lombok.Builder;

@Builder
public record ProductOrder(
    @Id Long id,
    long userId,
    long userAddressId,
    long shippingTypeId,
    @NonNull ShippingStatus shippingStatus,
    long paymentTypeId,
    @NonNull PaymentStatus paymentStatus,
    @NonNull OrderStatus orderStatus,
    String userNotes,
    @CreatedDate LocalDateTime createdAt,
    @LastModifiedDate LocalDateTime updatedAt) {

  public ProductOrderBuilder toBuilder() {
    return new ProductOrderBuilder()
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
