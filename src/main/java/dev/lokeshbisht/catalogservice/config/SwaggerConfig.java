package dev.lokeshbisht.catalogservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
            .components(new Components().addSecuritySchemes("TokenScheme",
                    new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                            .scheme("Token")
                            .name("Authorization")
                            .in(SecurityScheme.In.HEADER)))
            .info(new Info().title("Catalog Service API")
                    .description("Swagger for Catalog Service")
                    .version("v0.0.1"));
  }
}
