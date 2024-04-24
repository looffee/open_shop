package com.open.shop;

import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.lang.NonNull;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class MariaDbConfiguration extends AbstractR2dbcConfiguration {

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

  @Override
  @Bean
  public @NonNull ConnectionFactory connectionFactory() {

    MariadbConnectionConfiguration conf = MariadbConnectionConfiguration.builder()
        .host(host)
        .port(port)
        .username(username)
        .password(password)
        .database(database)
        .build();
    MariadbConnectionFactory factory = new MariadbConnectionFactory(conf);

    return factory;
  }

}
