package com.open.shop.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class SwaggerDocsSecurityCustomizer implements HttpSecurityCustomizer {

  @Override
  public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry t) {
    t
        .requestMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/v3/api-docs/swagger-config").permitAll()
        .requestMatchers(HttpMethod.GET, "/v3/api-docs").permitAll();
  }

}
