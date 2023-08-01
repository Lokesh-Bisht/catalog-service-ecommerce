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
