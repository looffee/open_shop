package com.open.shop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public record User(
    @Id @NonNull Long id,
    @NonNull String name,
    @NonNull String email,
    @NonNull String phone,
    String password) {
}
