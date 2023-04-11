package dev.lokeshbisht.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

  @NotNull(message = "Brand id is required.")
  @JsonProperty("brand_id")
  private Integer brandId;

  @NotNull(message = "Category id is required.")
  @JsonProperty("category_id")
  private Integer categoryId;

  @NotNull(message = "Product id is required.")
  @JsonProperty("product_id")
  private Integer productId;

  @NotBlank(message = "Brand name cannot be empty.")
  @JsonProperty("brand_name")
  private String brandName;

  @NotBlank(message = "Product name cannot be empty.")
  @JsonProperty("product_name")
  private String productName;

  @JsonProperty("description")
  private String description;

  @JsonProperty("photos")
  private List<String> photos;

  @JsonProperty("display_photo")
  private String displayPhoto;

  @JsonProperty("sold_count")
  private Long soldCount;

  @JsonProperty("return_count")
  private Long returnCount;
}