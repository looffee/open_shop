package com.open.shop.model.api;

import jakarta.validation.constraints.*;

public record UserDto(
    @Positive Long id,

    @NotBlank String firstName,

    @NotBlank String lastName,

    @NotBlank @Email String email,

    @NotBlank String phone) {

}
