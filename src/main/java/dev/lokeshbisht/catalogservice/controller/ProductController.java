package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.product.ProductDto;
import dev.lokeshbisht.catalogservice.dto.product.ProductSearchFilterDto;
import dev.lokeshbisht.catalogservice.dto.product.ProductSearchResponseDto;
import dev.lokeshbisht.catalogservice.entity.Product;
import dev.lokeshbisht.catalogservice.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/product")
@Validated
@Tag(name = "ProductController", description = "Product Controller")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Operation(summary = "createProduct")
  @PostMapping()
  public Product createProduct(@Valid @RequestBody ProductDto productDto) {
    return productService.createProduct(productDto);
  }

  @Operation(summary = "getProduct")
  @GetMapping("/{id}")
  public Optional<Product> getProduct(@PathVariable("id") String productId) {
    return productService.getProduct(productId);
  }

  @Operation(summary = "updateProduct")
  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable("id") String productId, @Valid @RequestBody ProductDto productDto) {
    return productService.updateProduct(productId, productDto);
  }

  @Operation(summary = "deleteProduct")
  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable("id") String productId) {
    productService.deleteProduct(productId);
  }

  @Operation(summary = "search")
  @PostMapping("/search")
  public ProductSearchResponseDto search(
          @RequestParam(defaultValue = "") String query,
          @RequestParam(defaultValue = "1") Integer page,
          @RequestParam(defaultValue = "5") Integer size,
          @RequestParam(defaultValue = "productId") String sort,
          @RequestParam(defaultValue = "ASC") String order,
          @RequestBody(required = false) ProductSearchFilterDto filter
  ) {
    Pageable pageable = order.equalsIgnoreCase("ASC") ?
            PageRequest.of(page, size, Sort.by(sort).ascending()) :
            PageRequest.of(page, size, Sort.by(sort).descending());
    return productService.search(query, pageable, filter);
  }

  @Operation(summary = "bulkCreateProduct")
  @PostMapping("/bulk")
  public void bulkCreateProduct(@Valid @RequestBody List<ProductDto> productDtoList) {
    productService.bulkCreateProduct(productDtoList);
  }
}
