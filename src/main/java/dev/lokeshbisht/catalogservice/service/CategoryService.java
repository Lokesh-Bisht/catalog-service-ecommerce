package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.category.CategoryDto;
import dev.lokeshbisht.catalogservice.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);
    Optional<Category> getCategory(String categoryId);
    Category updateCategory(String categoryId, CategoryDto categoryDto);
    void deleteCategory(String categoryId);
    List<Category> getAllCategories();
    void bulkCreateCategory(List<CategoryDto> categoryDtoList);
}
