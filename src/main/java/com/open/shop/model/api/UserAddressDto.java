package com.open.shop.model.api;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record UserAddressDto(
    @Positive Long id,

    @Positive Long userId,

    @NotBlank String address,

    @NotBlank String city,

    @NotBlank String state,

    @NotBlank String country,

    @NotBlank String zipCode) {

  public UserAddressDtoBuilder toBuilder() {
    return new UserAddressDtoBuilder()
        .id(id)
        .userId(userId)
        .address(address)
        .city(city)
        .state(state)
        .country(country)
        .zipCode(zipCode);
  }

}
