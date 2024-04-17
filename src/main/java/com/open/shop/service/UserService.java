package com.open.shop.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.open.shop.model.UserRoleEnum;
import com.open.shop.model.api.UserCredentials;
import com.open.shop.model.api.UserDto;
import com.open.shop.model.db.User;
import com.open.shop.model.db.UserRole;
import com.open.shop.repository.UserRepository;
import com.open.shop.repository.UserRoleRepository;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  @Autowired
  @NonNull
  UserRepository userRepository;

  @Autowired
  @NonNull
  UserRoleRepository userRoleRepository;

  @Autowired
  @NonNull
  ConversionService conversionService;

  @Autowired
  @NonNull
  PasswordEncoder passwordEncoder;

  Logger logger = LoggerFactory.getLogger(UserService.class);

  public Mono<UserDto> createUser(UserDto user) {
    return userRepository.save(
        new User(
            null,
            user.firstName(),
            user.lastName(),
            user.email(),
            user.phone(),
            null))
        .map(savedUser -> {
          return conversionService.convert(savedUser, UserDto.class);
        });
  }

  public Mono<UserDto> authenticateUser(UserCredentials userCredentials) {
    return userRepository.findByPhoneNumber(userCredentials.getPhoneNumber())
        .flatMap(user -> {
          if (user == null) {
            return Mono.error(new BadCredentialsException("Invalid credentials"));
          }

          if (passwordEncoder.matches(userCredentials.getPassword(), user.password())) {
            return Mono.just(conversionService.convert(user, UserDto.class));
          } else {
            return Mono.error(new BadCredentialsException("Invalid credentials"));
          }
        });
  }

  @PostConstruct
  private void createAdminUser() {
    String adminPhone = "1234567890";
    boolean isAlreadyCreated = userRepository.findByPhoneNumber(adminPhone).block() != null;

    if (isAlreadyCreated) {
      logger.info("Admin user already created");
      return;
    }

    String password = UUID.randomUUID().toString();
    User admin = new User(
        null,
        "admin",
        "admin",
        "admin@admin.admin",
        adminPhone,
        passwordEncoder.encode(password));

    logger.info("Creating admin user");
    logger.info("Admin phone: " + admin.phone());
    logger.info("Admin password: " + password);

    userRepository.save(admin).flatMap(savedUser -> {
      return userRoleRepository.save(new UserRole(null, savedUser.id(), UserRoleEnum.ADMIN));
    }).block();

    logger.info("Admin user created");
  }

}
