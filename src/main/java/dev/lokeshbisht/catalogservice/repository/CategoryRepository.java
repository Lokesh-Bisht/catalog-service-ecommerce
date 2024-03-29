package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    Optional<Category> findByCategoryId(Integer categoryId);
    void deleteByCategoryId(Integer categoryId);
}
