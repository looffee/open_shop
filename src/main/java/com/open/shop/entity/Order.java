package com.open.shop.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

public record Order(
        @Id Long id,
        long userId,
        long userAddressId,
        long shippingTypeId,
        int shippingStatus,
        long paymentTypeId,
        int paymentStatus,
        int orderStatus,
        String userNotes,
        @CreatedDate LocalDateTime createdAt,
        @LastModifiedDate LocalDateTime updatedAt) {

}
