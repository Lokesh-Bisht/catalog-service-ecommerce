package dev.lokeshbisht.catalogservice.controller;

import dev.lokeshbisht.catalogservice.dto.ProductDto;
import dev.lokeshbisht.catalogservice.entity.Product;
import dev.lokeshbisht.catalogservice.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product")
@Validated
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping()
  public Product addProduct(@Valid @RequestBody ProductDto productDto) {
    return productService.addProduct(productDto);
  }
}
