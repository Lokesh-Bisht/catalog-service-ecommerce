package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.dto.product.ProductSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.product.ProductSearchResponseDto;
import org.springframework.data.domain.Pageable;

public interface CustomProductRepository {

  ProductSearchResponseDto search(String searchQuery, Pageable pageable, ProductSearchFilterDto filter);
}
