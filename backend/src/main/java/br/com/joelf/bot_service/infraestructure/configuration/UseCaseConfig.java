package br.com.joelf.bot_service.infraestructure.configuration;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.usecase.*;
import br.com.joelf.bot_service.domain.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductDataProvider productDataProvider
    ) {
        return new CreateProductUseCaseImpl(productDataProvider);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(
            ProductDataProvider productDataProvider
    ) {
        return new UpdateProductUseCaseImpl(productDataProvider);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(
            ProductDataProvider productDataProvider
    ) {
        return new DeleteProductUseCaseImpl(productDataProvider);
    }

    @Bean
    public FindAllProductUseCase findAllProductUseCase(
            ProductDataProvider productDataProvider
    ) {
        return new FindAllProductUseCaseImpl(productDataProvider);
    }

    @Bean
    public CreateTemplateUseCase createTemplateUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new CreateTemplateUseCaseImpl(templateDataProvider);
    }

    @Bean
    public UpdateTemplateUseCase updateTemplateUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new UpdateTemplateUseCaseImpl(templateDataProvider);
    }
}
