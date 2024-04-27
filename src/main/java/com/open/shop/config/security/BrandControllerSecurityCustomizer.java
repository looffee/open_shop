package com.open.shop.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class BrandControllerSecurityCustomizer
    implements HttpSecurityCustomizer {

  @Override
  public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry t) {
    t
        .requestMatchers(HttpMethod.POST, "/api/brand/create").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/brand/update/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/brand/delete/**").hasAuthority("SCOPE_ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/brand/get/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/brand/get-all").permitAll();
  }

}
