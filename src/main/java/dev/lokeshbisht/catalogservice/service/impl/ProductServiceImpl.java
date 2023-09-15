package dev.lokeshbisht.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lokeshbisht.catalogservice.dto.product.*;
import dev.lokeshbisht.catalogservice.entity.Product;
import dev.lokeshbisht.catalogservice.exceptions.JsonRuntimeException;
import dev.lokeshbisht.catalogservice.exceptions.ProductAlreadyExistsException;
import dev.lokeshbisht.catalogservice.exceptions.ProductNotFoundException;
import dev.lokeshbisht.catalogservice.repository.CustomProductRepository;
import dev.lokeshbisht.catalogservice.repository.ProductRepository;
import dev.lokeshbisht.catalogservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomProductRepository customProductRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.product}")
    private String productTopic;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Product createProduct(ProductDto productDto) {
        logger.info("Saving product: {}", productDto);
        try {
            if (!productRepository.findByProductId(productDto.getProductId()).isEmpty()) {
                logger.error("Error occurred while saving product. Product already exists!");
                throw new ProductAlreadyExistsException("Product already exists!");
            }
            Product product = objectMapper.readValue(objectMapper.writeValueAsString(productDto), Product.class);
            logger.info("product = {}", product);
            product.setCreatedAt(Instant.now().getEpochSecond());
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
            updatedProduct.setCreatedAt(product.get().getCreatedAt());
            updatedProduct.setCreatedBy(product.get().getCreatedBy());
            updatedProduct.setUpdatedAt(Instant.now().getEpochSecond());
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

    @Override
    public ProductSearchResponseDto search(String searchQuery, Pageable pageable, ProductSearchFilterDto filters) {
        logger.info("Start product search with query: {} and filters: {}", searchQuery, filters);
        return customProductRepository.search(searchQuery, pageable, filters);
    }

    @Override
    public void bulkCreateProduct(List<ProductDto> productDtoList) {
        logger.info("Starting bulkCreateProduct");
//    for (ProductDto productDto : productDtoList) {
//      CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(productTopic, productDto.getProductId().toString(), productDto);
//      future.whenComplete((result, ex) -> {
//        if (ex == null) {
//          logger.info("Successfully pushed message: {} to kafka topic: {}", productDto, productTopic);
//        } else {
//          logger.error("Error while pushing message: {} to kafka topic: {}", productDto, productTopic);
//        }
//      });
//    }
    }

    @Override
    public MostAndLeastSoldProductsDto topFiveMostAndLeastSoldProducts() {
        return customProductRepository.topFiveMostAndLeastSoldProducts();
    }

    @Override
    public List<Product> getTopTenMostReturnProducts() {
        return customProductRepository.findTopTenMostReturnProducts();
    }

    @Override
    public Product updateProductSoldAndReturnCount(Integer productId, UpdateProductSoldAndReturnCountDto updateProductSoldAndReturnCountDto) {
        logger.info("Start updateProductSoldAndReturnCount for productId: {}, and data: {}", productId, updateProductSoldAndReturnCountDto);
        Product product = productRepository.findByProductId(productId).get();
        if (product == null) {
            throw new ProductNotFoundException("Product not found!");
        }
        product.setSoldCount(updateProductSoldAndReturnCountDto.getSoldCount());
        product.setReturnCount(updateProductSoldAndReturnCountDto.getReturnCount());
        product.setUpdatedBy(updateProductSoldAndReturnCountDto.getUpdatedBy());
        product.setUpdatedAt(Instant.now().getEpochSecond());
        logger.info("Updated product: {}", product);
        return productRepository.save(product);
    }
}
