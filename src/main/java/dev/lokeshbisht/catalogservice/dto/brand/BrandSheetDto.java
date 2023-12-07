/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

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
