package br.com.joelf.bot_service.infraestructure.configuration;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.infraestructure.dataprovider.ProductDataProviderImpl;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataProviderConfig {

    @Bean
    public ProductDataProvider productDataProvider(
            ModelMapper modelMapper,
            PgProductRepository pgProductRepository
    ) {
        return new ProductDataProviderImpl(modelMapper, pgProductRepository);
    }
}
