package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.product.*;
import dev.lokeshbisht.catalogservice.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(ProductDto productDto);
    Optional<Product> getProduct(String productId);
    Product updateProduct(String productId, ProductDto productDto);
    void deleteProduct(String productId);
    ProductSearchResponseDto search(String query, Pageable pageable, ProductSearchFilterDto filter);
    void bulkCreateProduct(List<ProductDto> productDtoList);
    MostAndLeastSoldProductsDto topFiveMostAndLeastSoldProducts();
    List<Product> getTopTenMostReturnProducts();
    Product updateProductSoldAndReturnCount(Integer productId, UpdateProductSoldAndReturnCountDto updateProductSoldAndReturnCountDto);
}
