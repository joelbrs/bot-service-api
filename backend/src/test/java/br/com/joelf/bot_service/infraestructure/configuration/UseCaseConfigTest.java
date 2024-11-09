package br.com.joelf.bot_service.infraestructure.configuration;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.usecase.CreateProductUseCaseImpl;
import br.com.joelf.bot_service.application.usecase.DeleteProductUseCaseImplTest;
import br.com.joelf.bot_service.application.usecase.FindAllProductUseCaseImpl;
import br.com.joelf.bot_service.application.usecase.UpdateProductUseCaseImpl;
import br.com.joelf.bot_service.domain.usecase.CreateProductUseCase;
import br.com.joelf.bot_service.domain.usecase.DeleteProductUseCase;
import br.com.joelf.bot_service.domain.usecase.FindAllProductUseCase;
import br.com.joelf.bot_service.domain.usecase.UpdateProductUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UseCaseConfigTest {

    @Mock
    private ProductDataProvider productDataProvider;

    private UseCaseConfig useCaseConfig;

    @BeforeEach
    void setUp() {
        useCaseConfig = new UseCaseConfig();
    }

    @Test
    void shouldReturnCorrectUpdateProductUseCase() {
        UpdateProductUseCase useCase = useCaseConfig.updateProductUseCase(productDataProvider);

        Assertions.assertNotNull(useCase, "UpdateProductUseCase should not be null");
        Assertions.assertInstanceOf(
            UpdateProductUseCaseImpl.class,
                useCase,
            "UpdateProductUseCase should be an instance of UpdateProductUseCaseImpl"
        );
    }

    @Test
    void shouldReturnCorrectCreateProductUseCase() {
        CreateProductUseCase useCase = useCaseConfig.createProductUseCase(productDataProvider);

        Assertions.assertNotNull(useCase, "CreateProductUseCase should not be null");
        Assertions.assertInstanceOf(
            CreateProductUseCaseImpl.class,
                useCase,
            "CreateProductUseCase should be an instance of CreateProductUseCaseImpl"
        );
    }

    @Test
    void shouldReturnCorrectDeleteProductUseCase() {
        DeleteProductUseCase useCase = useCaseConfig.deleteProductUseCase(productDataProvider);

        Assertions.assertNotNull(useCase, "ProductDataProvider should not be null");
        Assertions.assertInstanceOf(
            DeleteProductUseCaseImplTest.class,
            useCase,
            "ProductDataProvider should be an instance of ProductDataProviderImpl"
        );
    }

    @Test
    void shouldReturnCorrectFindAllPagedUseCase() {
        FindAllProductUseCase useCase = useCaseConfig.findAllProductUseCase(productDataProvider);

        Assertions.assertNotNull(useCase, "FindAllPagedUseCase should not be null");
        Assertions.assertInstanceOf(
            FindAllProductUseCaseImpl.class,
            useCase,
            "FindAllPagedUseCase should be an instance of FindAllPagedUseCaseImpl"
        );
    }
}
