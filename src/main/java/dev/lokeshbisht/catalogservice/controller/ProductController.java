package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.ProductDto;
import dev.lokeshbisht.catalogservice.entity.Product;
import dev.lokeshbisht.catalogservice.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/product")
@Validated
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping()
  public Product createProduct(@Valid @RequestBody ProductDto productDto) {
    return productService.createProduct(productDto);
  }

  @GetMapping("/{id}")
  public Optional<Product> getProduct(@PathVariable("id") String productId) {
    return productService.getProduct(productId);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable("id") String productId, @Valid @RequestBody ProductDto productDto) {
    return productService.updateProduct(productId, productDto);
  }
}
