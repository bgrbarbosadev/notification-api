package br.com.bgrbarbosa.notification_api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI DocApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Documentação da notification-api")
                                .description("API para controle de notificações")
                                .version("V1.0")
                                .contact(new Contact().name(": Bruno Gaspar Romeiro Barbosa")
                                        .email("bgrbarbosa.dev@gmail.com"))

                );
    }
}
