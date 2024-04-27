package com.open.shop.config;

import org.springframework.lang.NonNull;

public record DbCredentials(
    @NonNull String username,
    @NonNull String password,
    @NonNull String host,
    int port,
    @NonNull String database) {

}
