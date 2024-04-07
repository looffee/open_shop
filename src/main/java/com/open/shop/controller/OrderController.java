package com.open.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.entity.Order;
import com.open.shop.entity.OrderStatus;
import com.open.shop.entity.PaymentStatus;
import com.open.shop.entity.ShippingStatus;
import com.open.shop.repository.OrderRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/order")
public class OrderController {

  @Autowired
  @NonNull
  OrderRepository orderRepository;

  @PostMapping("/create")
  public Mono<Order> createOrder(@RequestBody Order order) {
    return orderRepository.save(
        order.builder()
            .id(null)
            .shippingStatus(ShippingStatus.PACKING)
            .paymentStatus(PaymentStatus.UNPAID)
            .orderStatus(OrderStatus.NEW)
            .createdAt(null)
            .updatedAt(null)
            .build());
  }

}
