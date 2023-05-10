package dev.lokeshbisht.catalogservice.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateProductSoldAndReturnCountDto {

  @JsonProperty("sold_count")
  private Long soldCount;

  @JsonProperty("return_count")
  private Long returnCount;

  @JsonProperty("updated_by")
  private String updatedBy;
}
