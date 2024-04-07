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

import com.open.shop.entity.Product;
import com.open.shop.repository.ProductRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  @NonNull
  ProductRepository productRepository;

  @PostMapping("/create")
  public Mono<Product> createProduct(@RequestBody Product product) {
    return productRepository.save(product.builder().id(null).createdAt(null).updatedAt(null).build());
  }

  @GetMapping("/get/{id}")
  public Mono<Product> getProduct(@PathVariable("id") long id) {
    return productRepository.findById(id);
  }

  @PostMapping("/update/{id}")
  public Mono<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
    return productRepository.save(product.builder().id(id).build());
  }

  @DeleteMapping("/delete/{id}")
  public Mono<Void> deleteProduct(@PathVariable("id") long id) {
    return productRepository.deleteById(id);
  }

  @GetMapping("/get-all")
  public Mono<List<Product>> getProducts() {
    return productRepository.findAll().collectList();
  }

}
