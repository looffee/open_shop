package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.ProductOrderDto;
import com.open.shop.model.db.ProductOrder;

@Service
public class ProductOrderToProductOrderDtoConverter implements Converter<ProductOrder, ProductOrderDto> {

  public ProductOrderDto convert(@NonNull ProductOrder productOrder) {
    return new ProductOrderDto(
        productOrder.id(),
        productOrder.userId(),
        productOrder.userAddressId(),
        productOrder.shippingTypeId(),
        productOrder.shippingStatus(),
        productOrder.paymentTypeId(),
        productOrder.paymentStatus(),
        productOrder.orderStatus(),
        productOrder.userNotes(),
        productOrder.createdAt(),
        productOrder.updatedAt());
  }

}
