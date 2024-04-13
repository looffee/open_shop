package com.open.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.db.User;
import com.open.shop.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {

  @Autowired
  @NonNull
  UserRepository userRepository;

  public Mono<User> createUser(User user) {
    return userRepository.save(
        new User(
            null,
            user.firstName(),
            user.lastName(),
            user.email(),
            user.phone(),
            null));
  }

}
