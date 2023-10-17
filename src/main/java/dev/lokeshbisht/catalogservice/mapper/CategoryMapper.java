package dev.lokeshbisht.catalogservice.mapper;

import dev.lokeshbisht.catalogservice.dto.category.CategoryDto;
import dev.lokeshbisht.catalogservice.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);
}
