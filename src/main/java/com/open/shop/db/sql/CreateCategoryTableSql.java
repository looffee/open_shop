package com.open.shop.db.sql;

public class CreateCategoryTableSql implements CreateTableSql {

  @Override
  public String sql() {
    return """
        CREATE TABLE IF NOT EXISTS category (
          id INT AUTO_INCREMENT NOT NULL,
          name VARCHAR(255) NOT NULL,
          description VARCHAR(255),
          parent_category_id INT,
          UNIQUE(name),
          PRIMARY KEY(id),
          CONSTRAINT fk_parent_category FOREIGN KEY (parent_category_id) REFERENCES category(id)
        ) ENGINE = InnoDB;
        """;
  }

}
