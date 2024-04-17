package com.open.shop.config;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  Logger logger = LoggerFactory.getLogger(SecurityConfig.class.getName());

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> {
          authorize
              .requestMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/v3/api-docs/swagger-config").permitAll()
              .requestMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
              .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
              .requestMatchers(HttpMethod.POST, "/api/auth/login-support").permitAll()
              .requestMatchers(HttpMethod.GET, "/api/shipping-type/get-all").permitAll()
              .requestMatchers(HttpMethod.POST, "/api/brand/create").hasAuthority("SCOPE_ADMIN")
              .anyRequest().authenticated();
        })
        .csrf(csrf -> csrf.disable())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

    SecurityFilterChain chain = http.build();

    return chain;
  }

  @Bean
  public JwtDecoder jwtDecoder(KeyPair keyPair) {
    return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
  }

  @Bean
  public JwtEncoder jwtEncoder(KeyPair keyPair) {
    JWK jwk = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).privateKey(keyPair.getPrivate()).build();
    ImmutableJWKSet<SecurityContext> jwkSet = new ImmutableJWKSet<SecurityContext>(new JWKSet(jwk));

    return new NimbusJwtEncoder(jwkSet);
  }

  @Bean
  public KeyPair keyPair() throws Exception {

    RSAKeyGenerator rsaKeyGenerator = new RSAKeyGenerator(2048);
    RSAKey rsaKey = rsaKeyGenerator.generate();
    KeyPair keyPair = new KeyPair(rsaKey.toPublicKey(), rsaKey.toPrivateKey());

    return keyPair;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
