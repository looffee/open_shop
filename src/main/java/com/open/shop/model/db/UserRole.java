package com.open.shop.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import com.open.shop.model.UserRoleEnum;

public record UserRole(
    @Id Long id,
    @NonNull Long userId,
    @NonNull UserRoleEnum role) {

}
