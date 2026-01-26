package py.edu.facitec.pinv01_267.pinv01_267_ws.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

  private static final String SECURITY_SCHEME_NAME = "bearerAuth";

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("API con Seguridad").version("1.0"))
        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
        .components(
            new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                    new SecurityScheme()
                        .name(SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
  }

  @Bean
  public OpenApiCustomizer hideSpecificEndpointsCustomizer() {
    return openApi -> {
      if (openApi.getPaths() != null) {
        openApi.getPaths().keySet().removeIf(path -> path.startsWith("/api/admin/") ||
            path.equals("/api/auth/login") ||
            path.equals("/api/suscripciones/save") ||
            path.equals("/api/lecturas/add"));
      }
      if (openApi.getComponents() != null && openApi.getComponents().getSchemas() != null) {
        openApi.getComponents().getSchemas().keySet()
            .removeIf(schemaName -> !schemaName.equals("ResponseDtoListPromedioLecturaDto") &&
                !schemaName.equals("PromedioLecturaDto") &&
                !schemaName.equals("ResponseDtoListDispositivoDto") &&
                !schemaName.equals("DispositivoDto"));
      }
    };
  }
}