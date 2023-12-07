/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.brand.BrandSearchResponseDto;
import dev.lokeshbisht.catalogservice.entity.Brand;
import dev.lokeshbisht.catalogservice.service.BrandService;
import dev.lokeshbisht.catalogservice.service.BulkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/brand")
@Validated
@Tag(name = "BrandController", description = "Brand Controller")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BulkService bulkService;

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

    @Operation(summary = "search")
    @PostMapping("/search")
    public BrandSearchResponseDto search(
        @RequestParam(defaultValue = "") String query,
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "5") Integer size,
        @RequestParam(defaultValue = "brandId") String sort,
        @RequestParam(defaultValue = "ASC") String order,
        @RequestBody(required = false)BrandSearchFilterDto filter
    ) {
        Pageable pageable = order.equalsIgnoreCase("ASC") ?
            PageRequest.of(page, size, Sort.by(sort).ascending()) :
            PageRequest.of(page, size, Sort.by(sort).descending());
        return brandService.search(query, pageable, filter);
    }

    @Operation(summary = "bulkCreateBrand")
    @PostMapping("/bulk")
    public void bulkCreateBrand(@Valid @RequestBody List<BrandDto> brandDtoList) {
        brandService.bulkCreateBrand(brandDtoList);
    }

    @PostMapping("/bulk/upload")
    public void bulkCreateBrandsFromFileUpload(@RequestPart("file") MultipartFile multipartFile, @RequestParam String userId) {
        bulkService.bulkCreateAndUpdateBrands(multipartFile);
    }
}
