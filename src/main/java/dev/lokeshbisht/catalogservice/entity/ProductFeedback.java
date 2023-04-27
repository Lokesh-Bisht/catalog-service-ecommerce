package dev.lokeshbisht.catalogservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("product_feedback")
public class ProductFeedback {

  @Id
  @JsonProperty("_id")
  private String id;

  @JsonProperty("user_id")
  private Integer userId;

  @JsonProperty("product_id")
  private Integer productId;

  private Integer rating;

  private String comment;

  private Long createdAt;

  private Long updatedAt;
}
