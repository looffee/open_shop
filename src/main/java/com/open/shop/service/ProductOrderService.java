package com.open.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.CreateProductOrder;
import com.open.shop.model.OrderStatus;
import com.open.shop.model.PaymentStatus;
import com.open.shop.model.ShippingStatus;
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

  public Mono<ProductOrder> createProductOrder(CreateProductOrder createProductOrder) {
    return Mono.just(null)
        .flatMap((o) -> {
          if (isUserNew(createProductOrder)) {
            return userService.createUser(createProductOrder.user())
                .flatMap(user -> {
                  return userAddressService
                      .createUserAddress(createProductOrder.userAddress())
                      .map(userAddress -> new UserAndAdressId(user.id(), userAddress.id()));
                });
          }

          return Mono.just(new UserAndAdressId(
              createProductOrder.user().id(),
              createProductOrder.userAddress().id()));
        })
        .flatMap(userAndAdressId -> {
          return productOrderRepository.save(
              ProductOrder.builder()
                  .id(null)
                  .userId(userAndAdressId.userId())
                  .userAddressId(userAndAdressId.addressId())
                  .shippingTypeId(createProductOrder.shippingTypeId())
                  .shippingStatus(ShippingStatus.PACKING)
                  .paymentTypeId(createProductOrder.paymentTypeId())
                  .paymentStatus(PaymentStatus.UNPAID)
                  .orderStatus(OrderStatus.NEW)
                  .userNotes(createProductOrder.userNotes())
                  .createdAt(null)
                  .updatedAt(null)
                  .build());
        });
  }

  private Boolean isUserNew(CreateProductOrder createProductOrder) {
    return createProductOrder.user().id() == null;
  }

  private record UserAndAdressId(long userId, long addressId) {
  }

}
