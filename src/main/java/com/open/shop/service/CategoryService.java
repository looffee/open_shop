package com.open.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.CategoryDto;
import com.open.shop.repository.CategoryRepository;

import reactor.core.publisher.Mono;

@Service
public class CategoryService {

  @Autowired
  @NonNull
  CategoryRepository categoryRepository;

  @Autowired
  @NonNull
  ConversionService conversionService;

  public Mono<List<CategoryDto>> getRootCategories() {
    return categoryRepository.findByParentCategoryIdIsNull()
        .map(category -> conversionService.convert(category, CategoryDto.class))
        .collectList();
  }

}
