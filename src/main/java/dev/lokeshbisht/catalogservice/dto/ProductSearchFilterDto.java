package dev.lokeshbisht.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductSearchFilterDto {

  @JsonProperty("brand_id")
  private Integer brandId;

  @JsonProperty("category_id")
  private Integer categoryId;

  @JsonProperty("min_price")
  private Integer minPrice;

  @JsonProperty("max_price")
  private Integer maxPrice;
}
