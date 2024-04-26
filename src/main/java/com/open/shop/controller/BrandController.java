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

import com.open.shop.model.db.Brand;
import com.open.shop.repository.BrandRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

  @Autowired
  @NonNull
  BrandRepository brandRepository;

  @Operation(summary = "Create a brand", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/create")
  public Mono<Brand> createBrand(@RequestBody Brand brand) {
    return brandRepository.save(new Brand(null, brand.name(), brand.description()));
  }

  @Operation(summary = "Get a brand by id")
  @GetMapping("/get/{id}")
  public Mono<Brand> getBrand(@PathVariable long id) {
    return brandRepository.findById(id);
  }

  @Operation(summary = "Update a brand by id", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/update/{id}")
  public Mono<Brand> updateBrand(@PathVariable("id") long id, @RequestBody Brand brand) {
    return brandRepository.save(new Brand(id, brand.name(), brand.description()));
  }

  @Operation(summary = "Delete a brand by id", security = @SecurityRequirement(name = "bearerAuth"))
  @DeleteMapping("/delete/{id}")
  public Mono<Void> deleteBrand(@PathVariable("id") long id) {
    return brandRepository.deleteById(id);
  }

  @Operation(summary = "Get all brands")
  @GetMapping("/get-all")
  public Mono<List<Brand>> getBrands() {
    return brandRepository.findAll().collectList();
  }
}
