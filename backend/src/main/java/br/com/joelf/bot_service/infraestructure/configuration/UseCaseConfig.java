package br.com.joelf.bot_service.infraestructure.configuration;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.usecase.CreateProductUseCaseImpl;
import br.com.joelf.bot_service.application.usecase.DeleteProductUseCaseImpl;
import br.com.joelf.bot_service.application.usecase.FindAllProductUseCaseImpl;
import br.com.joelf.bot_service.application.usecase.UpdateProductUseCaseImpl;
import br.com.joelf.bot_service.domain.usecase.CreateProductUseCase;
import br.com.joelf.bot_service.domain.usecase.DeleteProductUseCase;
import br.com.joelf.bot_service.domain.usecase.FindAllProductUseCase;
import br.com.joelf.bot_service.domain.usecase.UpdateProductUseCase;
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
}
