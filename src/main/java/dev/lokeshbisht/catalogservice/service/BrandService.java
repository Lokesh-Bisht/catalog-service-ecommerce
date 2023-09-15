package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchResponseDto;
import dev.lokeshbisht.catalogservice.entity.Brand;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    Brand createBrand(BrandDto brandDto);
    Optional<Brand> getBrand(String brandId);
    Brand updateBrand(String brandId, BrandDto brandDto);
    void deleteBrand(String brandId);
    List<Brand> getAllBrandsByCategoryId(String categoryId);
    BrandSearchResponseDto search(String query, Pageable page, BrandSearchFilterDto filter);
    void bulkCreateBrand(List<BrandDto> brandDtoList);
}
