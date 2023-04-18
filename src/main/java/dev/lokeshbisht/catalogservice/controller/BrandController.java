package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.brand.BrandDto;
import dev.lokeshbisht.catalogservice.entity.Brand;
import dev.lokeshbisht.catalogservice.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
