package com.open.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.lang.NonNull;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class DbConnectionFactoryConfiguration extends AbstractR2dbcConfiguration {

  @Value("${DB_TYPE:mySql}")
  private String dbType;

  @Autowired
  @NonNull
  ApplicationContext applicationContext;

  @Override
  public @NonNull ConnectionFactory connectionFactory() {
    return (ConnectionFactory) applicationContext.getBean(dbType + "ConnectionFactory");
  }

}
