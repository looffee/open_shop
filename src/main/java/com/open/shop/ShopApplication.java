package com.open.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;

import com.open.shop.db.TablesCreator;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ShopApplication {

  @Autowired
  @NonNull
  ApplicationContext context;

  @Autowired
  @NonNull
  TablesCreator tablesCreator;

  public static void main(String[] args) {
    SpringApplication.run(ShopApplication.class, args);
  }

  @PostConstruct
  public void init() {
    tablesCreator.createTables();
  }

}
