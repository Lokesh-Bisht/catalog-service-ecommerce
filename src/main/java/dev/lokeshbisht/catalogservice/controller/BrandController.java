package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.entity.Brand;
import dev.lokeshbisht.catalogservice.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/brand")
@Validated
@Tag(name = "BrandController", description = "Brand Controller")
public class BrandController {

  @Autowired
  private BrandService brandService;

  @Operation(summary = "createBrand")
  @PostMapping()
  public Brand createBrand(@Valid @RequestBody BrandDto brandDto) {
    return brandService.createBrand(brandDto);
  }

  @Operation(summary = "createBrand")
  @GetMapping("/{id}")
  public Optional<Brand> getBrand(@PathVariable("id") String brandId) {
    return brandService.getBrand(brandId);
  }

  @Operation(summary = "updateBrand")
  @PutMapping("/{id}")
  public Brand updateBrand(@PathVariable("id") String brandId, @Valid @RequestBody BrandDto brandDto) {
    return brandService.updateBrand(brandId, brandDto);
  }

  @Operation(summary = "deleteBrand")
  @DeleteMapping("/{id}")
  public void deleteBrand(@PathVariable("id") String brandId) {
    brandService.deleteBrand(brandId);
  }

  @Operation(summary = "getAllBrandsByCategoryId")
  @GetMapping("/category/{category_id}")
  public List<Brand> getAllBrandsByCategoryId(@PathVariable("category_id") String categoryId) {
    return brandService.getAllBrandsByCategoryId(categoryId);
  }

  @Operation(summary = "bulkCreateBrand")
  @PostMapping("/bulk")
  public void bulkCreateBrand(@Valid @RequestBody List<BrandDto> brandDtoList) {
    brandService.bulkCreateBrand(brandDtoList);
  }
}
