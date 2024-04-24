package com.open.shop.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  Logger logger = LoggerFactory.getLogger(SecurityConfig.class.getName());

  @Bean
  public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
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
        .oauth2ResourceServer(jwt -> jwt.jwt(Customizer.withDefaults()))
        .csrf(csrf -> csrf.disable());

    SecurityFilterChain chain = http.build();

    return chain;
  }

  @Bean
  public JwtDecoder jwtDecoder(KeyPair keyPair) throws Exception {

    return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
  }

  @Bean
  public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {

    return new NimbusJwtEncoder(jwkSource);
  }

  @Bean
  public KeyPair keyPair() {
    return generateRsaKey();
  }

  @Bean
  public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    RSAKey rsaKey = new RSAKey.Builder(publicKey)
        .privateKey(privateKey)
        .keyID(UUID.randomUUID().toString())
        .build();
    JWKSet jwkSet = new JWKSet(rsaKey);
    return new ImmutableJWKSet<>(jwkSet);
  }

  private static KeyPair generateRsaKey() {
    KeyPair keyPair;
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      keyPair = keyPairGenerator.generateKeyPair();
    } catch (Exception ex) {
      throw new IllegalStateException(ex);
    }
    return keyPair;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

  @Bean
  public AuthenticationEventPublisher authenticationEventPublisher(
      ApplicationEventPublisher applicationEventPublisher) {
    return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
  }

}
