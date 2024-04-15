package com.open.shop.db.sql;

public class CreateProductOrderTableSql implements CreateTableSql {

  @Override
  public String sql() {
    return """
        CREATE TABLE IF NOT EXISTS product_order (
          id INT AUTO_INCREMENT NOT NULL,
          user_id INT NOT NULL,
          user_address_id INT NOT NULL,
          shipping_type_id INT NOT NULL,
          shipping_status VARCHAR(255) NOT NULL,
          payment_type_id INT NOT NULL,
          payment_status VARCHAR(255) NOT NULL,
          order_status VARCHAR(255) NOT NULL,
          user_notes VARCHAR(255),
          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
            ON UPDATE CURRENT_TIMESTAMP,
          PRIMARY KEY(id),
          CONSTRAINT fk_product_order_user FOREIGN KEY (user_id) REFERENCES user(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
          CONSTRAINT fk_product_order_user_address FOREIGN KEY (user_address_id) REFERENCES user_address(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
          CONSTRAINT fk_product_order_shipping_type FOREIGN KEY (shipping_type_id) REFERENCES shipping_type(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
          CONSTRAINT fk_product_order_payment_type FOREIGN KEY (payment_type_id) REFERENCES payment_type(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
        )
        """;
  }
}
