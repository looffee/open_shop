package com.open.shop.config;

import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class MariaDbConfiguration {

  @Autowired
  @NonNull
  DbCredentials dbCredentials;

  @Bean(name = "mariaDbConnectionFactory")
  public @NonNull ConnectionFactory connectionFactory() {

    MariadbConnectionConfiguration conf = MariadbConnectionConfiguration.builder()
        .host(dbCredentials.host())
        .port(dbCredentials.port())
        .username(dbCredentials.username())
        .password(dbCredentials.password())
        .database(dbCredentials.database())
        .build();
    MariadbConnectionFactory factory = new MariadbConnectionFactory(conf);

    return factory;
  }

}
