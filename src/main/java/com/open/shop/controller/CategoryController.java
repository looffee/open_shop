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

import com.open.shop.model.api.CategoryDto;
import com.open.shop.model.db.Category;
import com.open.shop.repository.CategoryRepository;
import com.open.shop.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  @NonNull
  CategoryRepository categoryRepository;

  @Autowired
  @NonNull
  CategoryService categoryService;

  @Operation(summary = "Create a category", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/create")
  public Mono<Category> createCategory(@RequestBody Category category) {
    return categoryRepository
        .save(new Category(null, category.name(), category.description(), category.parentCategoryId()));
  }

  @Operation(summary = "Get a category by id")
  @GetMapping("/get/{id}")
  public Mono<Category> getCategory(@PathVariable("id") long id) {
    return categoryRepository.findById(id);
  }

  @Operation(summary = "Update a category by id", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/update/{id}")
  public Mono<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
    return categoryRepository
        .save(new Category(id, category.name(), category.description(), category.parentCategoryId()));
  }

  @Operation(summary = "Delete a category by id", security = @SecurityRequirement(name = "bearerAuth"))
  @DeleteMapping("/delete/{id}")
  public Mono<Void> deleteCategory(@PathVariable("id") long id) {
    return categoryRepository.deleteById(id);
  }

  @Operation(summary = "Get all categories")
  @GetMapping("/get-all")
  public Mono<List<Category>> getCategories() {
    return categoryRepository.findAll().collectList();
  }

  @Operation(summary = "Get root categories")
  @GetMapping("/get-root")
  public Mono<List<CategoryDto>> getRootCategories() {
    return categoryService.getRootCategories();
  }

}
