package com.open.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbCredentialsConfiguration {

  @Value("${DB_HOST:localhost}")
  private String host;
  @Value("${DB_PORT:3306}")
  private int port;
  @Value("${DB_USERNAME:root}")
  private String username;
  @Value("${DB_PASSWORD:my-secret-pw}")
  private String password;
  @Value("${DB_DATABASE:open_shop_test}")
  private String database;

  @Bean
  public DbCredentials getDbCredentials() {
    return new DbCredentials(username, password, host, port, database);
  }

}
