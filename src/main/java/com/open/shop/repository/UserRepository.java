package com.open.shop.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.model.db.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

  @Query("SELECT * FROM user WHERE phone = :phoneNumber")
  public Mono<User> findByPhoneNumber(String phoneNumber);

}
