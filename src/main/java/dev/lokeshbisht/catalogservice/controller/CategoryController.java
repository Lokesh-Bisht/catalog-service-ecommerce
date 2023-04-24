package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.category.CategoryDto;
import dev.lokeshbisht.catalogservice.entity.Category;
import dev.lokeshbisht.catalogservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/category")
@Validated
@Tag(name = "CategoryController", description = "Category Controller")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @Operation(summary = "createCategory")
  @PostMapping()
  Category createCategory(@Valid @RequestBody CategoryDto categoryDto) {
    return categoryService.createCategory(categoryDto);
  }

  @Operation(summary = "getCategory")
  @GetMapping("/{id}")
  Optional<Category> getCategory(@PathVariable("id") String categoryId) {
    return categoryService.getCategory(categoryId);
  }
}
