package com.open.shop.model.api;

import jakarta.validation.constraints.*;

public record ShippingTypeDto(
    @Positive Long id,
    @NotBlank String name,
    @Positive @NotNull double price,
    @NotBlank String description) {

}
