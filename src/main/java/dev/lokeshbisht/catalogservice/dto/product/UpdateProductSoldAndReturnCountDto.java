/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

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
