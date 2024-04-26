package com.open.shop.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class ShippingTypeControllerSecurityCustomizer implements HttpSecurityCustomizer {

  @Override
  public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry t) {
    t
        .requestMatchers(HttpMethod.POST, "/api/shipping-type/create").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/shipping-type/update/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/shipping-type/delete/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/shipping-type/get-all").permitAll();
  }

}
