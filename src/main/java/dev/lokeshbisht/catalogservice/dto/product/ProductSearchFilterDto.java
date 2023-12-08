/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package dev.lokeshbisht.catalogservice.dto.product;

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
