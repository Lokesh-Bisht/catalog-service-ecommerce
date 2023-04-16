package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

  Optional<Product> findByProductId(Integer productId);
  void deleteByProductId(Integer productId);
}
