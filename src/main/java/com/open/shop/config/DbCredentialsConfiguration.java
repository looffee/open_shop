package com.open.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbCredentialsConfiguration {

  @Value("${com.open.shop.db.host}")
  private String host;
  @Value("${com.open.shop.db.port}")
  private int port;
  @Value("${com.open.shop.db.username}")
  private String username;
  @Value("${com.open.shop.db.password}")
  private String password;
  @Value("${com.open.shop.db.database}")
  private String database;

  @Bean
  public DbCredentials getDbCredentials() {
    return new DbCredentials(username, password, host, port, database);
  }

}
