package com.open.shop.db.sql;

public class CreateProductTableSql implements CreateTableSql {

  @Override
  public String sql() {
    return """
        CREATE TABLE IF NOT EXISTS product (
          id INT AUTO_INCREMENT NOT NULL,
          name VARCHAR(255) NOT NULL,
          price DOUBLE NOT NULL,
          description VARCHAR(255) NOT NULL,
          image_url VARCHAR(255),
          category_id INT NOT NULL,
          brand_id INT NOT NULL,
          color VARCHAR(255),
          size VARCHAR(255),
          weight VARCHAR(255),
          material VARCHAR(255),
          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
            ON UPDATE CURRENT_TIMESTAMP,
          UNIQUE(name),
          PRIMARY KEY(id),
          CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
          CONSTRAINT fk_product_brand FOREIGN KEY (brand_id) REFERENCES brand(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
        ) ENGINE = InnoDB;
        """;
  }

}
