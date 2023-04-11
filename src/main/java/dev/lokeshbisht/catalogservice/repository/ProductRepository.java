package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
