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
