package com.open.shop.db.sql;

public class CreateUserAddressTableSql implements CreateTableSql {

  @Override
  public String sql() {
    return """
        CREATE TABLE IF NOT EXISTS user_address (
          id INT AUTO_INCREMENT NOT NULL,
          user_id INT NOT NULL,
          address VARCHAR(255) NOT NULL,
          city VARCHAR(255) NOT NULL,
          state VARCHAR(255) NOT NULL,
          country VARCHAR(255) NOT NULL,
          zip_code VARCHAR(255) NOT NULL,
          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
          PRIMARY KEY(id),
          CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
        )
        """;
  }

}
