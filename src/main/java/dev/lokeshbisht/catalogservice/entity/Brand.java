package dev.lokeshbisht.catalogservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@Data
public class Brand {

  @Id
  @JsonProperty("_id")
  private String id;

  @JsonProperty("brand_id")
  private Integer brandId;

  @JsonProperty("category_id")
  private Integer categoryId;

  @JsonProperty("brand_name")
  @TextIndexed
  private String brandName;

  @TextIndexed
  private String description;

  @JsonProperty("brand_logo")
  private String brandLogo;

  @JsonProperty("created_at")
  private Long createdAt;

  @JsonProperty("created_by")
  private String createdBy;

  @JsonProperty("updated_at")
  private Long updatedAt;

  @JsonProperty("updated_by")
  private String updatedBy;
}
