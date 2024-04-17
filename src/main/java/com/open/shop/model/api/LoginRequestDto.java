package com.open.shop.model.api;

import jakarta.validation.constraints.*;

public record LoginRequestDto(
    @NotBlank String phoneNumber,
    @NotBlank @Size(min = 8) String password) implements UserCredentials {

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getPhoneNumber() {
    return phoneNumber;
  }

}
