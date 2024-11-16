package br.com.joelf.bot_service.infraestructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig {

    @Value("${security.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer addCorsMappings() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedHeaders("Content-Type", "Accept")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(Boolean.TRUE)
                ;
            }
        };
    }
}
