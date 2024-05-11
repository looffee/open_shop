package com.open.shop.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.open.shop.model.db.Category;

import reactor.core.publisher.Flux;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {

  @Query("SELECT * FROM category WHERE parent_category_id IS NULL")
  public Flux<Category> findByParentCategoryIdIsNull();

}
