package com.open.shop.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public record Brand(@Id Long id, @NonNull String name, String description) {
}
