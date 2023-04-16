package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.ProductDto;
import dev.lokeshbisht.catalogservice.entity.Product;
import dev.lokeshbisht.catalogservice.exceptions.JsonRuntimeException;
import dev.lokeshbisht.catalogservice.exceptions.ProductNotFoundException;
import dev.lokeshbisht.catalogservice.repository.ProductRepository;
import dev.lokeshbisht.catalogservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

  @Override
  public Optional<Product> getProduct(String productId) {
    logger.info("Find product with id: {}", productId);
    Optional<Product> product = productRepository.findByProductId(Integer.parseInt(productId));
    if (product.isEmpty()) {
      logger.error("Product not found!");
      throw new ProductNotFoundException("Product not found.");
    }
    return product;
  }

  @Override
  public Product updateProduct(String productId, ProductDto productDto) {
    logger.info("Update product: {} with id: {}", productDto, productId);
    Optional<Product> product = productRepository.findByProductId(Integer.parseInt(productId));
    if (product.isEmpty()) {
      logger.error("Product not found!");
      throw new ProductNotFoundException("Product not found.");
    }
    try {
      Product updatedProduct = objectMapper.readValue(objectMapper.writeValueAsString(productDto), Product.class);
      updatedProduct.setId(product.get().getId());
      Product result = productRepository.save(updatedProduct);
      logger.info("Successfully updated product.");
      return result;
    } catch (JsonMappingException e) {
      logger.error("Error occurred during updating document: {}", productDto);
      throw new JsonRuntimeException("Json Mapping exception encountered during object to string conversion", e);
    } catch (JsonProcessingException e) {
      logger.error("Error occurred during updating document: {}", productDto);
      throw new JsonRuntimeException("Json Processing exception encountered during object to string conversion", e);
    }
  }

  @Override
  public void deleteProduct(String productId) {
    Optional<Product> product = productRepository.findByProductId(Integer.parseInt(productId));
    if (product.isEmpty()) {
      logger.error("Error deleting product with id: {}", productId);
      throw new ProductNotFoundException("Product not found!");
    }
    productRepository.deleteByProductId(Integer.parseInt(productId));
    logger.info("Successfully deleted product with id: {}", productId);
  }
}
