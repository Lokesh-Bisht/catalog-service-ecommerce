package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.category.CategoryDto;
import dev.lokeshbisht.catalogservice.entity.Category;
import dev.lokeshbisht.catalogservice.exceptions.CategoryAlreadyExistsException;
import dev.lokeshbisht.catalogservice.exceptions.CategoryNotFoundException;
import dev.lokeshbisht.catalogservice.exceptions.JsonRuntimeException;
import dev.lokeshbisht.catalogservice.repository.CategoryRepository;
import dev.lokeshbisht.catalogservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ObjectMapper objectMapper;

  private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

  @Override
  public Category createCategory(CategoryDto categoryDto) {
    logger.info("Saving category: {}", categoryDto);
    if (!categoryRepository.findByCategoryId(categoryDto.getCategoryId()).isEmpty()) {
      logger.error("Error occurred while saving category. Category already exists!");
      throw new CategoryAlreadyExistsException("Category Already Exists.");
    }
    try {
      Category category = objectMapper.readValue(objectMapper.writeValueAsString(categoryDto), Category.class);
      category.setCreatedAt(Instant.now().getEpochSecond());
      logger.info("Successfully added category: {}", category);
      return categoryRepository.save(category);
    } catch (JsonMappingException e) {
      logger.error("Error occurred during saving document: {}", categoryDto);
      throw new JsonRuntimeException("Json Mapping exception encountered during object to string conversion", e);
    } catch (JsonProcessingException e) {
      logger.error("Error occurred during saving document: {}", categoryDto);
      throw new JsonRuntimeException("Json Processing exception encountered during object to string conversion.", e);
    }
  }

  @Override
  public Optional<Category> getCategory(String categoryId) {
    logger.info("Get category with id: {}", categoryId);
    if (categoryRepository.findByCategoryId(Integer.parseInt(categoryId)).isEmpty()) {
      logger.error("Category not found!");
      throw new CategoryNotFoundException("Category not found!");
    }
    return categoryRepository.findByCategoryId(Integer.parseInt(categoryId));
  }

  @Override
  public Category updateCategory(String categoryId, CategoryDto categoryDto) {
    logger.info("Update category: {} with id: {}", categoryDto, categoryId);
    Optional<Category> category = categoryRepository.findByCategoryId(Integer.parseInt(categoryId));
    if (category.isEmpty()) {
      logger.error("Category with id {} is not found.", categoryId);
      throw new CategoryNotFoundException("Category not found!");
    }
    try {
      Category updatedCategory = objectMapper.readValue(objectMapper.writeValueAsString(categoryDto), Category.class);
      updatedCategory.setId(category.get().getId());
      updatedCategory.setUpdatedAt(Instant.now().getEpochSecond());
      logger.info("Successfully updated category: {}", updatedCategory);
      return categoryRepository.save(updatedCategory);
    } catch (JsonMappingException e) {
      logger.error("Error occurred during updating document: {}", categoryDto);
      throw new JsonRuntimeException("Json Mapping exception encountered during object to string conversion", e);
    } catch (JsonProcessingException e) {
      logger.error("Error occurred during updating document: {}", categoryDto);
      throw new JsonRuntimeException("Json Processing exception encountered during object to string conversion", e);
    }

  }
}
