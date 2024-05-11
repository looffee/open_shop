package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.CategoryDto;
import com.open.shop.model.db.Category;

@Service
public class CategoryDtoToCategoryConverter implements Converter<CategoryDto, Category> {

  @Override
  @Nullable
  public Category convert(@NonNull CategoryDto source) {
    return new Category(
        source.id(),
        source.name(),
        source.description(),
        source.parentCategoryId());
  }

}
