/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package dev.lokeshbisht.catalogservice.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BulkCreateOrUpdateDto {

    @JsonProperty("brand")
    private BrandDto brandDto;

    private String operation;

    @JsonProperty("metadata")
    private BulkCreateOrUpdateMetadataDto bulkCreateOrUpdateMetadataDto;
}
