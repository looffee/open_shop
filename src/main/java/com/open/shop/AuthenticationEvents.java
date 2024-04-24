package com.open.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

  Logger logger = LoggerFactory.getLogger(getClass());

  @EventListener
  public void onSuccess(AuthenticationSuccessEvent success) {
    logger.info("Authentication success: " + success.getAuthentication().getName());
  }

  @EventListener
  public void onFailure(AbstractAuthenticationFailureEvent failures) {
    logger.info("Authentication failure: " + failures.getException().getMessage());
  }

  @EventListener
  public void onAutorizationFailure(AuthorizationDeniedEvent<?> failures) {
    logger.info("Authorization failure: " + failures.getAuthentication().get().getAuthorities());
  }
}
