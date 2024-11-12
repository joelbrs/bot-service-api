package br.com.joelf.bot_service.infraestructure.configuration;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.usecase.*;
import br.com.joelf.bot_service.domain.usecase.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductDataProvider productDataProvider,
            SubProductDataProvider subProductDataProvider
    ) {
        return new CreateProductUseCaseImpl(productDataProvider, subProductDataProvider);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(
            ProductDataProvider productDataProvider,
            SubProductDataProvider subProductDataProvider
    ) {
        return new UpdateProductUseCaseImpl(productDataProvider, subProductDataProvider);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(
            ProductDataProvider productDataProvider,
            SubProductDataProvider subProductDataProvider
    ) {
        return new DeleteProductUseCaseImpl(productDataProvider, subProductDataProvider);
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

    @Bean
    public FindAllTemplateUseCase findAllTemplateUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new FindAllTemplateUseCaseImpl(templateDataProvider);
    }

    @Bean
    public DeleteTemplateUseCase deleteTemplateUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new DeleteTemplateUseCaseImpl(templateDataProvider);
    }

    @Bean
    public FindProductByIdUseCase findProductByIdUseCase(
            ModelMapper modelMapper,
            ProductDataProvider productDataProvider,
            SubProductDataProvider subProductDataProvider
    ) {
        return new FindProductByIdUseCaseImpl(
                modelMapper,
                productDataProvider,
                subProductDataProvider
        );
    }

    @Bean
    public FindTemplateByIdUseCase findTemplateByIdUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new FindTemplateByIdUseCaseImpl(templateDataProvider);
    }
}
