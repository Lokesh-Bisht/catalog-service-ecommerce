package dev.lokeshbisht.catalogservice.dto.brand;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class BrandSheetDto {

    @JsonAlias("brand id")
    private String brandId;

    @JsonAlias("brand name")
    private String brandName;

    @JsonAlias("category")
    private String categoryId;

    @JsonAlias("description")
    private String description;
}
