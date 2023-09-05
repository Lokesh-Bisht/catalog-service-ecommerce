package dev.lokeshbisht.catalogservice.repository.impl;

import dev.lokeshbisht.catalogservice.entity.ProductFeedback;
import dev.lokeshbisht.catalogservice.repository.CustomProductFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomProductFeedbackRepositoryImpl implements CustomProductFeedbackRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ProductFeedback findProductFeedbackByUser(Integer productId, Integer userId) {
        Query query = new Query(Criteria.where("productId").is(productId).andOperator(Criteria.where("userId").is(userId)));
        return mongoTemplate.findOne(query, ProductFeedback.class);
    }

    @Override
    public void deleteProductFeedbackByUserAndProductId(Integer productId, Integer userId) {
        Query query = new Query(Criteria.where("productId").is(productId).andOperator(Criteria.where("userId").is(userId)));
        mongoTemplate.remove(query, ProductFeedback.class);
    }
}
