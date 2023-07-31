package dev.lokeshbisht.catalogservice.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BulkCreateOrUpdateMetadataDto {

    @JsonProperty("brand_id")
    private Integer brandId;
}
