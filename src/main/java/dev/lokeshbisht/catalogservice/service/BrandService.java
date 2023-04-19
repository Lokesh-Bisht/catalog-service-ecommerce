package dev.lokeshbisht.catalogservice.service;

import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.entity.Brand;

import java.util.Optional;

public interface BrandService {

  Brand createBrand(BrandDto brandDto);
  Optional<Brand> getBrand(String brandId);
  Brand updateBrand(String brandId, BrandDto brandDto);
}
