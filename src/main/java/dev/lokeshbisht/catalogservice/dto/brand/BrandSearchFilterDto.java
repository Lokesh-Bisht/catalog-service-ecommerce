package dev.lokeshbisht.catalogservice.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrandSearchFilterDto {

  @JsonProperty("category_id")
  private String categoryId;
}
