package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.category.CategoryDto;
import dev.lokeshbisht.catalogservice.entity.Category;

import java.util.Optional;

public interface CategoryService {

  Category createCategory(CategoryDto categoryDto);
  Optional<Category> getCategory(String categoryId);
}
