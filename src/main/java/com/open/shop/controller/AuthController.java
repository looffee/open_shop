package com.open.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.model.api.LoginRequestDto;
import com.open.shop.service.AuthService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  @NonNull
  AuthService authService;

  @PostMapping("/login")
  public Mono<String> login(@RequestBody LoginRequestDto loginRequestDto) {
    return authService.login(loginRequestDto);
  }

}
