package com.open.shop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public record User(
    @Id Long id,
    @NonNull String firstName,
    @NonNull String lastName,
    @NonNull String email,
    @NonNull String phone,
    String password) {
}
