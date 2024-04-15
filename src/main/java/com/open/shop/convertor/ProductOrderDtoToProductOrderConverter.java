package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.ProductOrderDto;
import com.open.shop.model.db.ProductOrder;

@Service
public class ProductOrderDtoToProductOrderConverter implements Converter<ProductOrderDto, ProductOrder> {

  public ProductOrder convert(@NonNull ProductOrderDto productOrderDto) {
    return new ProductOrder(
        productOrderDto.id(),
        productOrderDto.userId(),
        productOrderDto.userAddressId(),
        productOrderDto.shippingTypeId(),
        productOrderDto.shippingStatus(),
        productOrderDto.paymentTypeId(),
        productOrderDto.paymentStatus(),
        productOrderDto.orderStatus(),
        productOrderDto.userNotes(),
        productOrderDto.createdAt(),
        productOrderDto.updatedAt());
  }

}
