package br.com.joelf.bot_service.infraestructure.configuration;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.infraestructure.dataprovider.ProductDataProviderImpl;
import br.com.joelf.bot_service.infraestructure.dataprovider.SubProductDataProviderImpl;
import br.com.joelf.bot_service.infraestructure.dataprovider.TemplateDataProviderImpl;
import br.com.joelf.bot_service.infraestructure.dataprovider.UserDataProviderImpl;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgSubProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgTemplateRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgUserRepository;
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

    @Bean
    public TemplateDataProvider templateDataProvider(
            ModelMapper modelMapper,
            PgTemplateRepository pgTemplateRepository
    ) {
        return new TemplateDataProviderImpl(modelMapper, pgTemplateRepository);
    }

    @Bean
    public SubProductDataProvider subProductDataProvider(
            ModelMapper modelMapper,
            PgSubProductRepository pgSubProductRepository
    ) {
        return new SubProductDataProviderImpl(modelMapper, pgSubProductRepository);
    }

    @Bean
    public UserDataProvider userDataProvider(
            ModelMapper modelMapper,
            PgUserRepository pgUserRepository
    ) {
        return new UserDataProviderImpl(modelMapper, pgUserRepository);
    }
}
