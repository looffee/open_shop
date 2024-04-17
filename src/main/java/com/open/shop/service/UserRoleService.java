package com.open.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.db.UserRole;
import com.open.shop.repository.UserRoleRepository;

import reactor.core.publisher.Mono;

@Service
public class UserRoleService {

  @Autowired
  @NonNull
  UserRoleRepository userRoleRepository;

  public Mono<List<UserRole>> getRolesByUserId(long userId) {
    return userRoleRepository.findByUserId(userId).collectList();
  }

}
