package com.example.config;

import static java.net.URI.create;
import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.temporaryRedirect;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class OpenApiConfig {

  private static final String SCHEMA_NAME = "basicAuth";

  @Bean
  public RouterFunction<ServerResponse> routerFunction() {
    return route(GET("/"), request -> temporaryRedirect(create("/swagger-ui/index.html")).build());
  }

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(getInfo())
        .components(getComponents())
        .security(List.of(new SecurityRequirement().addList(SCHEMA_NAME)));
  }

  private Info getInfo() {
    return new Info()
        .title("Restaurant Project")
        .description("API Endpoints")
        .summary("POC")
        .version("0.0.1-SNAPSHOT")
        .termsOfService("DEMO")
        .contact(new Contact().email("abc@123.com").url("www.google.com").name("ASD"))
        .license(new License().name("Test").identifier("MIT").url("www.facebook.com"));
  }

  private Components getComponents() {
    return new Components()
        .addSecuritySchemes(
            SCHEMA_NAME,
            new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .description("Basic authentication"));
  }
}
