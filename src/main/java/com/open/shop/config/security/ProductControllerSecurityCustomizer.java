package com.open.shop.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class ProductControllerSecurityCustomizer implements HttpSecurityCustomizer {

  @Override
  public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry t) {
    t
        .requestMatchers(HttpMethod.POST, "/api/product/create").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/product/update/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/product/delete/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/product/get/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/product/get-all").permitAll();
  }

}
