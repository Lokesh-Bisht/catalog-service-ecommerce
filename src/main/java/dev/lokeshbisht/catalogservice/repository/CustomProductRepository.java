package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.dto.product.MostAndLeastSoldProductsDto;
import dev.lokeshbisht.catalogservice.dto.product.ProductSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.product.ProductSearchResponseDto;
import dev.lokeshbisht.catalogservice.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomProductRepository {

  ProductSearchResponseDto search(String searchQuery, Pageable pageable, ProductSearchFilterDto filter);
  MostAndLeastSoldProductsDto topFiveMostAndLeastSoldProducts();
  List<Product> findTopTenMostReturnProducts();
}
