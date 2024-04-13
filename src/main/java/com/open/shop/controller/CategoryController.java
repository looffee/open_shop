package com.open.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.shop.model.db.Category;
import com.open.shop.repository.CategoryRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  @NonNull
  CategoryRepository categoryRepository;

  @PostMapping("/create")
  public Mono<Category> createCategory(@RequestBody Category category) {
    return categoryRepository
        .save(new Category(null, category.name(), category.description(), category.parentCategoryId()));
  }

  @GetMapping("/get/{id}")
  public Mono<Category> getCategory(@PathVariable("id") long id) {
    return categoryRepository.findById(id);
  }

  @PostMapping("/update/{id}")
  public Mono<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
    return categoryRepository
        .save(new Category(id, category.name(), category.description(), category.parentCategoryId()));
  }

  @DeleteMapping("/delete/{id}")
  public Mono<Void> deleteCategory(@PathVariable("id") long id) {
    return categoryRepository.deleteById(id);
  }

  @GetMapping("/get-all")
  public Mono<List<Category>> getCategories() {
    return categoryRepository.findAll().collectList();
  }

}
