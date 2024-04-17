package com.open.shop.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.model.db.UserRole;

import reactor.core.publisher.Flux;

public interface UserRoleRepository extends ReactiveCrudRepository<UserRole, Long> {

  @Query("SELECT * FROM user_role WHERE user_id = :userId")
  public Flux<UserRole> findByUserId(long userId);

}
