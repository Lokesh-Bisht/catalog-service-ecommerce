package dev.lokeshbisht.catalogservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.util.List;

@Data
public class Product {

    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("brand_id")
    private Integer brandId;

    @JsonProperty("category_id")
    private Integer categoryId;

    @JsonProperty("product_id")
    @TextIndexed
    private Integer productId;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("product_name")
    @TextIndexed
    private String productName;

    private String description;

    private List<String> photos;

    @JsonProperty("display_photo")
    private String displayPhoto;

    @JsonProperty("sold_count")
    private Long soldCount;

    @JsonProperty("return_count")
    private Long returnCount;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
