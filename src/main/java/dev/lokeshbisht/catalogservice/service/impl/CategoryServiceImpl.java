package dev.lokeshbisht.catalogservice.service.impl;

import dev.lokeshbisht.catalogservice.dto.category.CategoryDto;
import dev.lokeshbisht.catalogservice.entity.Category;
import dev.lokeshbisht.catalogservice.exceptions.CategoryAlreadyExistsException;
import dev.lokeshbisht.catalogservice.exceptions.CategoryNotFoundException;
import dev.lokeshbisht.catalogservice.mapper.CategoryMapper;
import dev.lokeshbisht.catalogservice.repository.CategoryRepository;
import dev.lokeshbisht.catalogservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.category}")
    private String categoryTopic;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        logger.info("Saving category: {}", categoryDto);
        if (categoryRepository.findByCategoryId(categoryDto.getCategoryId()).isPresent()) {
            logger.error("Error occurred while saving category. Category already exists!");
            throw new CategoryAlreadyExistsException("Category Already Exists.");
        }
        Category category = categoryMapper.toCategory(categoryDto);
        category.setCreatedAt(Instant.now().getEpochSecond());
        logger.info("Successfully added category: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategory(String categoryId) {
        logger.info("Get category with id: {}", categoryId);
        if (categoryRepository.findByCategoryId(Integer.parseInt(categoryId)).isEmpty()) {
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
        Category updatedCategory = categoryMapper.toCategory(categoryDto);
        updatedCategory.setId(category.get().getId());
        updatedCategory.setCreatedBy(category.get().getCreatedBy());
        updatedCategory.setCreatedAt(category.get().getCreatedAt());
        updatedCategory.setUpdatedAt(Instant.now().getEpochSecond());
        logger.info("Successfully updated category: {}", updatedCategory);
        return categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryId) {
        logger.info("Delete category with id: {}", categoryId);
        if (categoryRepository.findByCategoryId(Integer.parseInt(categoryId)).isEmpty()) {
            throw new CategoryNotFoundException("Category not found!");
        }
        categoryRepository.deleteByCategoryId(Integer.parseInt(categoryId));
        logger.info("Category with id {} is deleted successfully.", categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        logger.info("Get all categories.");
        return categoryRepository.findAll();
    }

    @Override
    public void bulkCreateCategory(List<CategoryDto> categoryDtoList) {
        logger.info("Starting bulkCreateCategory");
//    for (CategoryDto categoryDto : categoryDtoList) {
//      CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(categoryTopic, categoryDto.getCategoryId().toString(), categoryDto);
//      future.whenComplete((result, ex) -> {
//        if (ex == null) {
//          logger.info("Successfully pushed message: {} to kafka topic: {}", categoryDto, categoryTopic);
//        } else {
//          logger.error("Error while pushing message: {} to kafka topic: {}", categoryDto, categoryTopic);
//        }
//      });
//    }
    }
}
