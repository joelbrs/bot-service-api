package br.com.joelf.bot_service.infraestructure.repositories.clients.meta.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

public class MetaConfig {

    @Value("${meta.whatsapp.access-token}")
    private String accessToken;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
    }
}
