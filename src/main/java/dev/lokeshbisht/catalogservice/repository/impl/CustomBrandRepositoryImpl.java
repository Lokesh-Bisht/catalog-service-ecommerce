package dev.lokeshbisht.catalogservice.repository.impl;

import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchResponseDto;
import dev.lokeshbisht.catalogservice.entity.Brand;
import dev.lokeshbisht.catalogservice.repository.CustomBrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomBrandRepositoryImpl implements CustomBrandRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  private static final Logger logger = LoggerFactory.getLogger(CustomBrandRepositoryImpl.class);

  @Override
  public BrandSearchResponseDto search(String searchQuery, Pageable pageable, BrandSearchFilterDto filter) {
    Query query = new Query();
    List<Criteria> criteriaList = new ArrayList<>();
    if (filter != null) {
      if (filter.getCategoryId() != null) {
        criteriaList.add(Criteria.where("categoryId").is(filter.getCategoryId()));
      }
      query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
    }
    if (!"".equals(searchQuery)) {
      query.addCriteria(TextCriteria.forDefaultLanguage().caseSensitive(false).matching(searchQuery));
    }
    Long count = mongoTemplate.count(query, Brand.class);
    query.with(pageable);
    logger.info("Brand search query = {}", query);
    List<Brand> result = mongoTemplate.find(query, Brand.class);
    return new BrandSearchResponseDto(result, pageable.getPageNumber(), pageable.getPageSize(), count);
  }
}
