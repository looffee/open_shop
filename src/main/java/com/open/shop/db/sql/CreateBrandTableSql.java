package com.open.shop.db.sql;

public class CreateBrandTableSql implements CreateTableSql {

  @Override
  public String sql() {
    return """
        CREATE TABLE IF NOT EXISTS brand (
          id INT AUTO_INCREMENT NOT NULL,
          name VARCHAR(255) NOT NULL,
          description VARCHAR(255),
          PRIMARY KEY(id));
        """;
  }

}
