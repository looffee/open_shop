package com.open.shop.db.sql;

public class CreatePaymentTypeTableSql implements CreateTableSql {

  @Override
  public String sql() {
    return """
        CREATE TABLE IF NOT EXISTS payment_type (
          id INT AUTO_INCREMENT NOT NULL,
          name VARCHAR(255) NOT NULL,
          description VARCHAR(255) NOT NULL,
          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
            ON UPDATE CURRENT_TIMESTAMP,
          UNIQUE(name),
          PRIMARY KEY(id)
        ) ENGINE = InnoDB
        """;
  }

}
