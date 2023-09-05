package dev.lokeshbisht.catalogservice.repository.impl;

import dev.lokeshbisht.catalogservice.dto.product.MostAndLeastSoldProductsDto;
import dev.lokeshbisht.catalogservice.dto.product.ProductSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.product.ProductSearchResponseDto;
import dev.lokeshbisht.catalogservice.entity.Product;
import dev.lokeshbisht.catalogservice.repository.CustomProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CustomProductRepositoryImpl.class);

    @Override
    public ProductSearchResponseDto search(String searchQuery, Pageable pageable, ProductSearchFilterDto filter) {
        List<Criteria> criteriaList = new ArrayList<>();
        Query query = new Query();
        if (filter != null) {
            if (filter.getBrandId() != null) {
                criteriaList.add(Criteria.where("brandId").is(filter.getBrandId()));
            }
            if (filter.getCategoryId() != null) {
                criteriaList.add(Criteria.where("categoryId").is(filter.getCategoryId()));
            }
            if (filter.getMinPrice() != null && filter.getMaxPrice() != null) {
                criteriaList.add(Criteria.where("price").gte(filter.getMinPrice()).lte(filter.getMaxPrice()));
            }
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        }

        if (!"".equals(searchQuery)) {
            query.addCriteria(TextCriteria.forDefaultLanguage().caseSensitive(false).matching(searchQuery));
        }
        Long count = mongoTemplate.count(query, Product.class);
        query.with(pageable);
        logger.debug("Product search query = {}", query);
        List<Product> result = mongoTemplate.find(query, Product.class);
        return new ProductSearchResponseDto(result, pageable.getPageNumber(), pageable.getPageSize(), count);
    }

    @Override
    public MostAndLeastSoldProductsDto topFiveMostAndLeastSoldProducts() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "soldCount")).limit(5);
        logger.info("Executing query to find top five most sold products: {}", query);
        List<Product> mostSold = mongoTemplate.find(query, Product.class);
        query.with(Sort.by(Sort.Direction.ASC, "soldCount")).limit(5);
        logger.info("Executing query to find top five least sold products: {}", query);
        List<Product> leastSold = mongoTemplate.find(query, Product.class);
        return new MostAndLeastSoldProductsDto(mostSold, leastSold);
    }

    @Override
    public List<Product> findTopTenMostReturnProducts() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "returnCount")).limit(10);
        return mongoTemplate.find(query, Product.class);
    }
}
