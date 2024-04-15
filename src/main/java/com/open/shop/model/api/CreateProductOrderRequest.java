package com.open.shop.model.api;

import jakarta.validation.constraints.*;

public record CreateProductOrderRequest(
    @NotNull UserDto user,
    @NotNull UserAddressDto userAddress,
    @NotEmpty CreateProductOrderItem[] productOrderItems,
    @Positive @NotNull long shippingTypeId,
    @Positive @NotNull long paymentTypeId,
    String userNotes) {

  public record CreateProductOrderItem(
      @Positive @NotNull long productId,
      @Positive @NotNull int quantity) {
  }

}
