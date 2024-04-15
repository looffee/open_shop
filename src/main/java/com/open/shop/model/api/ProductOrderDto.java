package com.open.shop.model.api;

import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import com.open.shop.model.OrderStatus;
import com.open.shop.model.PaymentStatus;
import com.open.shop.model.ShippingStatus;

public record ProductOrderDto(
    long id,
    long userId,
    long userAddressId,
    long shippingTypeId,
    @NonNull ShippingStatus shippingStatus,
    long paymentTypeId,
    @NonNull PaymentStatus paymentStatus,
    @NonNull OrderStatus orderStatus,
    String userNotes,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {

}
