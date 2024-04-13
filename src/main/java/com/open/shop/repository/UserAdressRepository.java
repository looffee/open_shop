package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.model.db.UserAddress;

public interface UserAdressRepository extends ReactiveCrudRepository<UserAddress, Long> {

}
