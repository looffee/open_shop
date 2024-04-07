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

import com.open.shop.entity.Brand;
import com.open.shop.repository.BrandRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

  @Autowired
  @NonNull
  BrandRepository brandRepository;

  @PostMapping("/create")
  public Mono<Brand> createBrand(@RequestBody Brand brand) {
    return brandRepository.save(new Brand(null, brand.name(), brand.description()));
  }

  @GetMapping("/get/{id}")
  public Mono<Brand> getBrand(@PathVariable long id) {
    return brandRepository.findById(id);
  }

  @PostMapping("/update/{id}")
  public Mono<Brand> updateBrand(@PathVariable("id") long id, @RequestBody Brand brand) {
    return brandRepository.save(new Brand(id, brand.name(), brand.description()));
  }

  @DeleteMapping("/delete/{id}")
  public Mono<Void> deleteBrand(@PathVariable("id") long id) {
    return brandRepository.deleteById(id);
  }

  @GetMapping("/get-all")
  public Mono<List<Brand>> getBrands() {
    return brandRepository.findAll().collectList();
  }
}
