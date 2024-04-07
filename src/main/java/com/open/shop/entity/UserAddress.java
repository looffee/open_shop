package com.open.shop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public record UserAddress(
    @Id @NonNull Long id,
    @NonNull Long userId,
    @NonNull String address,
    @NonNull String city,
    @NonNull String state,
    @NonNull String country,
    @NonNull String zipCode) {

}
