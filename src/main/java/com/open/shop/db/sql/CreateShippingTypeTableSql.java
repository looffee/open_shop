package com.open.shop.db.sql;

public class CreateShippingTypeTableSql implements CreateTableSql {

  @Override
  public String sql() {
    return """
        CREATE TABLE IF NOT EXISTS shipping_type (
          id INT AUTO_INCREMENT NOT NULL,
          name VARCHAR(255) NOT NULL,
          description VARCHAR(255) NOT NULL,
          price DECIMAL(10, 2) NOT NULL,
          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
            ON UPDATE CURRENT_TIMESTAMP,
          UNIQUE(name),
          PRIMARY KEY(id)
        ) ENGINE = InnoDB
        """;
  }

}
