package com.open.shop.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;
import lombok.Builder;

@Builder
public record Product(
        @Id Long id,
        @NonNull String name,
        double price,
        String description,
        String imageUrl,
        @NonNull Long categoryId,
        @NonNull Long brandId,
        String color,
        String size,
        String weight,
        String material,
        @CreatedDate LocalDateTime createdAt,
        @LastModifiedDate LocalDateTime updatedAt) {

    public ProductBuilder builder() {
        return new ProductBuilder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .imageUrl(imageUrl)
                .categoryId(categoryId)
                .brandId(brandId)
                .color(color)
                .size(size)
                .weight(weight)
                .material(material)
                .createdAt(createdAt)
                .updatedAt(updatedAt);
    }
}
