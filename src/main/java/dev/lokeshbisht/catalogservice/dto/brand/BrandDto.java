/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package dev.lokeshbisht.catalogservice.dto.brand;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDto {

    @NotNull(message = "Brand id is required.")
    @JsonProperty("brand_id")
    private Integer brandId;

    @NotNull(message = "Category id is required.")
    @JsonProperty("category_id")
    private Integer categoryId;

    @NotBlank(message = "Brand name cannot be empty.")
    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("description")
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
