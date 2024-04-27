package com.open.shop.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class ProductOrderControllerSecurityCustomizer implements HttpSecurityCustomizer {

  @Override
  public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry t) {
    t
        .requestMatchers(HttpMethod.POST, "/api/product-order/create").permitAll();
  }

}
