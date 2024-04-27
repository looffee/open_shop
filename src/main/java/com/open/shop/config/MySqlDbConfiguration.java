package com.open.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class MySqlDbConfiguration {

  @Autowired
  @NonNull
  DbCredentials dbCredentials;

  @Bean(name = "mySqlConnectionFactory")
  public @NonNull ConnectionFactory connectionFactory() {
    MySqlConnectionConfiguration conf = MySqlConnectionConfiguration.builder()
        .host(dbCredentials.host())
        .port(dbCredentials.port())
        .username(dbCredentials.username())
        .password(dbCredentials.password())
        .database(dbCredentials.database())
        .build();

    return MySqlConnectionFactory.from(conf);
  }

}
