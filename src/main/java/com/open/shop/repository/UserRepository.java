package com.open.shop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.open.shop.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
