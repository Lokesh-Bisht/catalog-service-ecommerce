package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.entity.ProductFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeedbackRepository extends MongoRepository<ProductFeedback, String> {
}
