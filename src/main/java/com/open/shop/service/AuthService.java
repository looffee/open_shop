package com.open.shop.service;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.UserCredentials;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

  @Autowired
  @NonNull
  JwtEncoder jwtEncoder;

  @Autowired
  @NonNull
  UserService userService;

  @Autowired
  @NonNull
  UserRoleService userRoleService;

  Logger logger = LoggerFactory.getLogger(AuthService.class);

  public Mono<String> login(UserCredentials userCredentials) {

    return userService.authenticateUser(userCredentials)
        .flatMap(user -> {
          return userRoleService.getRolesByUserId(user.id())
              .map(roles -> {
                Instant now = Instant.now();

                JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                    .issuer("self")
                    .subject(userCredentials.getPhoneNumber())
                    .issuedAt(now)
                    .expiresAt(now.plus(Duration.ofHours(1)))
                    .claim("scope", roles
                        .stream()
                        .map(role -> role.role().toString())
                        .reduce("", (a, b) -> a + " " + b))
                    .claim("user", user)
                    .build();

                logger.debug("Jwt scope: " + claimsSet.getClaim("scope"));

                return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
              });
        });
  }

}
