package com.open.shop;

import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.lang.NonNull;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class MariaDbConfiguration extends AbstractR2dbcConfiguration {

  @Override
  @Bean
  public @NonNull ConnectionFactory connectionFactory() {

    MariadbConnectionConfiguration conf = MariadbConnectionConfiguration.builder()
        .host("localhost")
        .port(3306)
        .username("root")
        .password("my-secret-pw")
        .database("open_shop")
        .build();
    MariadbConnectionFactory factory = new MariadbConnectionFactory(conf);

    return factory;
  }

}
