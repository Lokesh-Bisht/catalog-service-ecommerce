package dev.lokeshbisht.catalogservice.dto.feedback;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFeedbackDto {

  @NotNull(message = "UserId is required.")
  @JsonProperty("user_id")
  private Integer userId;

  @NotNull(message = "ProductId is required.")
  @JsonProperty("product_id")
  private Integer productId;

  @NotNull(message = "Rating is required.")
  @JsonProperty("rating")
  private Integer rating;

  @JsonProperty("comment")
  private String comment;

  @JsonProperty("created_at")
  private Long createdAt;

  @JsonProperty("updated_at")
  private Long updatedAt;
}
