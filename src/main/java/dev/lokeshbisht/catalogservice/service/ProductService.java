package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.ProductDto;
import dev.lokeshbisht.catalogservice.entity.Product;

import java.util.Optional;

public interface ProductService {

  Product createProduct(ProductDto productDto);
  Optional<Product> getProduct(String productId);
  Product updateProduct(String productId, ProductDto productDto);
  void deleteProduct(String productId);
}
