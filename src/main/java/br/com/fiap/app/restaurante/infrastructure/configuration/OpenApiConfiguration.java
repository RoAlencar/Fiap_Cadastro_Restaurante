package br.com.fiap.app.restaurante.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cadastra Restaurante API")
                        .version("v1.0")
                        .description("API para cadastro e gerenciamento de estabelecimentos do sistema de gestão de restuarantes"));
    }
}
