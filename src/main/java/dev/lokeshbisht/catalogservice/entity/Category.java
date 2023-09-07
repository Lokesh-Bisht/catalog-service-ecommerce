package dev.lokeshbisht.catalogservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Category {

    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("category_id")
    private Integer categoryId;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
