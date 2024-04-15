package com.open.shop.model.api;

import jakarta.validation.constraints.*;

public record PaymentTypeDto(
    @Positive Long id,
    @NotBlank String name,
    @NotBlank String description) {

}
