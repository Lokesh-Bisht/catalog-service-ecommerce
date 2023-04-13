package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.ProductDto;
import dev.lokeshbisht.catalogservice.entity.Product;
import dev.lokeshbisht.catalogservice.exceptions.JsonRuntimeException;
import dev.lokeshbisht.catalogservice.repository.ProductRepository;
import dev.lokeshbisht.catalogservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ObjectMapper objectMapper;

  private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

  @Override
  public Product createProduct(ProductDto productDto) {
    logger.info("Saving product: {}", productDto);
    try {
      Product product = objectMapper.readValue(objectMapper.writeValueAsString(productDto), Product.class);
      logger.info("product = {}", product);
      Product result = productRepository.save(product);
      logger.info("Successfully added product: {}", result);
      return result;
    } catch (JsonProcessingException e) {
      logger.error("Error occurred during saving document: {}", productDto);
      throw new JsonRuntimeException("Json Processing exception encountered during object to string conversion.", e);
    }
  }
}
