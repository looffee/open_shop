package com.open.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.db.ProductOrderItem;
import com.open.shop.repository.ProductOrderItemRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductOrderItemService {

  @Autowired
  @NonNull
  ProductOrderItemRepository productOrderItemRepository;

  public Mono<ProductOrderItem> createProductOrderItem(ProductOrderItem productOrderItem) {
    return productOrderItemRepository.save(
        ProductOrderItem.builder()
            .id(null)
            .productOrderId(productOrderItem.productOrderId())
            .productId(productOrderItem.productId())
            .quantity(productOrderItem.quantity())
            .price(productOrderItem.price())
            .discount(productOrderItem.discount())
            .build());
  }

}
