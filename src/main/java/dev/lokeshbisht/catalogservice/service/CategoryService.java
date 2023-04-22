package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.category.CategoryDto;
import dev.lokeshbisht.catalogservice.entity.Category;

public interface CategoryService {

  Category createCategory(CategoryDto categoryDto);
}
