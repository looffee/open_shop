package com.open.shop.model.api;

import jakarta.validation.constraints.*;

public record CategoryDto(
    @Positive Long id,
    @NotBlank String name,
    String description,
    Long parentCategoryId) {

}
