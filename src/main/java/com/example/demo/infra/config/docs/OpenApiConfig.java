package com.example.demo.infra.config.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
  @Bean
  OpenAPI defaultOpenApiConfig() {
    Info info = new Info();

    info.setTitle("Demo API");
    info.setDescription("API for demo purposes");
    info.setVersion("0.0.1: Alpha");

    Server localhostServer = new Server();
    localhostServer.setUrl("http://localhost:9988");
    localhostServer.setDescription("Localhost");

    Server productionServer = new Server();
    productionServer.setUrl("https://api.victorborzaquel.com");
    productionServer.setDescription("Production");

    return new OpenAPI()
        .info(info)
        .addServersItem(productionServer)
        .addServersItem(localhostServer);
  }
}