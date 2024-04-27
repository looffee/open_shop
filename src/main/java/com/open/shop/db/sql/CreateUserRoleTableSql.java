package com.open.shop.db.sql;

public class CreateUserRoleTableSql implements CreateTableSql {

  @Override
  public String sql() {
    return """
        CREATE TABLE IF NOT EXISTS user_role (
          id INT AUTO_INCREMENT NOT NULL,
          user_id INT NOT NULL,
          role ENUM('USER', 'ADMIN') NOT NULL,
          PRIMARY KEY(id),
          CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES user(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
        ) ENGINE = InnoDB
        """;
  }

}
