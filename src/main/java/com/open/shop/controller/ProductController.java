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

import com.open.shop.model.db.Product;
import com.open.shop.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  @NonNull
  ProductRepository productRepository;

  @Operation(summary = "Create a product", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/create")
  public Mono<Product> createProduct(@RequestBody Product product) {
    return productRepository.save(product.toBuilder().id(null).createdAt(null).updatedAt(null).build());
  }

  @Operation(summary = "Get a product by id")
  @GetMapping("/get/{id}")
  public Mono<Product> getProduct(@PathVariable("id") long id) {
    return productRepository.findById(id);
  }

  @Operation(summary = "Update a product by id", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/update/{id}")
  public Mono<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
    return productRepository.save(product.toBuilder().id(id).build());
  }

  @Operation(summary = "Delete a product by id", security = @SecurityRequirement(name = "bearerAuth"))
  @DeleteMapping("/delete/{id}")
  public Mono<Void> deleteProduct(@PathVariable("id") long id) {
    return productRepository.deleteById(id);
  }

  @Operation(summary = "Get all products")
  @GetMapping("/get-all")
  public Mono<List<Product>> getProducts() {
    return productRepository.findAll().collectList();
  }

}
