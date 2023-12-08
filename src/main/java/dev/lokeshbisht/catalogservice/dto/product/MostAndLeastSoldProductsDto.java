/**
 * Copyright (C) 2023 Lokesh Bisht
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package dev.lokeshbisht.catalogservice.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lokeshbisht.catalogservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MostAndLeastSoldProductsDto {

    @JsonProperty("most_sold")
    List<Product> mostSold;

    @JsonProperty("least_sold")
    List<Product> leastSold;
}
