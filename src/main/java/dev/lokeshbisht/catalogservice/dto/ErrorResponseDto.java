package dev.lokeshbisht.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lokeshbisht.catalogservice.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {

  @JsonProperty("error_code")
  private ErrorCode errorCode;

  @JsonProperty("error_message")
  private String errorMessage;
}
