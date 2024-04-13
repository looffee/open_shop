package com.open.shop.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public record UserAddress(
    @Id Long id,
    long userId,
    @NonNull String address,
    @NonNull String city,
    @NonNull String state,
    @NonNull String country,
    @NonNull String zipCode) {

}
