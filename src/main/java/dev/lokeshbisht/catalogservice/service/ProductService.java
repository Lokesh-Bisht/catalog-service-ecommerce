package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.ProductDto;
import dev.lokeshbisht.catalogservice.entity.Product;

public interface ProductService {

  Product createProduct(ProductDto productDto);
}
