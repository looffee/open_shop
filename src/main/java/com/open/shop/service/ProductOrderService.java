package com.open.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.OrderStatus;
import com.open.shop.model.PaymentStatus;
import com.open.shop.model.ShippingStatus;
import com.open.shop.model.api.CreateProductOrderRequest;
import com.open.shop.model.api.ProductOrderDto;
import com.open.shop.model.db.ProductOrder;
import com.open.shop.repository.ProductOrderRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductOrderService {

  @Autowired
  @NonNull
  UserService userService;

  @Autowired
  @NonNull
  UserAddressService userAddressService;

  @Autowired
  @NonNull
  ProductOrderRepository productOrderRepository;

  @Autowired
  @NonNull
  ConversionService conversionService;

  public Mono<ProductOrderDto> createProductOrder(CreateProductOrderRequest request) {
    return Mono.just(1)
        .flatMap((o) -> {
          if (isUserNew(request)) {
            return userService.createUser(request.user())
                .flatMap(user -> {
                  return userAddressService
                      .createUserAddress(
                          request
                              .userAddress()
                              .toBuilder()
                              .userId(user.id())
                              .build())
                      .map(userAddress -> new UserAndAdressId(user.id(), userAddress.id()));
                });
          }

          return Mono.just(new UserAndAdressId(
              request.user().id(),
              request.userAddress().id()));
        })
        .flatMap(userAndAdressId -> {
          return productOrderRepository.save(
              ProductOrder.builder()
                  .id(null)
                  .userId(userAndAdressId.userId())
                  .userAddressId(userAndAdressId.addressId())
                  .shippingTypeId(request.shippingTypeId())
                  .shippingStatus(ShippingStatus.PACKING)
                  .paymentTypeId(request.paymentTypeId())
                  .paymentStatus(PaymentStatus.UNPAID)
                  .orderStatus(OrderStatus.NEW)
                  .userNotes(request.userNotes())
                  .createdAt(null)
                  .updatedAt(null)
                  .build())
              .map(savedProductOrder -> {
                return conversionService.convert(savedProductOrder, ProductOrderDto.class);
              });
        });
  }

  private Boolean isUserNew(CreateProductOrderRequest createProductOrder) {
    return createProductOrder.user().id() == null;
  }

  private record UserAndAdressId(long userId, long addressId) {
  }

}
