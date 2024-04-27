package com.open.shop.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class CategoryControllerSecurityCustomizer implements HttpSecurityCustomizer {

  @Override
  public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry t) {
    t
        .requestMatchers(HttpMethod.POST, "/api/category/create").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/category/update/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/category/delete/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/category/get/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/category/get-all").permitAll();
  }

}
