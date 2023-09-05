package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchResponseDto;
import org.springframework.data.domain.Pageable;

public interface CustomBrandRepository {
    BrandSearchResponseDto search(String searchQuery, Pageable pageable, BrandSearchFilterDto filter);
}
