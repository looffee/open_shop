package com.open.shop.model.api;

import java.io.Serializable;

import jakarta.validation.constraints.*;

public record UserDto(
    @Positive Long id,

    @NotBlank String firstName,

    @NotBlank String lastName,

    @NotBlank @Email String email,

    @NotBlank String phone) implements Serializable {

}
