package com.open.shop.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import com.open.shop.db.sql.*;

@Component
public class TablesCreator {

  @Autowired
  @NonNull
  DatabaseClient databaseClient;

  public void createTables() {
    CreateTableSql[] createTableSqls = {
        new CreateCategoryTableSql(),
        new CreateBrandTableSql(),
        new CreatePaymentTypeTableSql(),
        new CreateShippingTypeTableSql(),
        new CreateUserTableSql(),
        new CreateUserAddressTableSql(),
        new CreateProductTableSql(),
        new CreateProductOrderTableSql(),
        new CreateUserRoleTableSql()
    };

    for (CreateTableSql createTableSql : createTableSqls) {
      createTableFromSql(createTableSql.sql());
    }
  }

  public void createTableFromSql(String sql) {
    databaseClient.sql(sql).fetch().rowsUpdated().block();
  }

}
