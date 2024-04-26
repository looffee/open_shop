package com.open.shop.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class PaymentTypeSecurityCustomizer implements HttpSecurityCustomizer {

  @Override
  public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry t) {
    t
        .requestMatchers(HttpMethod.POST, "/api/payment-type/create").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/payment-type/update/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/payment-type/delete/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/payment-type/get-all").permitAll();
  }

}
