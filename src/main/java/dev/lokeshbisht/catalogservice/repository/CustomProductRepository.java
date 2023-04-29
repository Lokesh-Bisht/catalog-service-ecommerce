package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.dto.ProductSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.ProductSearchResponseDto;
import org.springframework.data.domain.Pageable;

public interface CustomProductRepository {

  ProductSearchResponseDto search(String searchQuery, Pageable pageable, ProductSearchFilterDto filter);
}
