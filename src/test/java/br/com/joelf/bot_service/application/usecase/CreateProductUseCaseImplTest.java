package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.domain.entities.SubProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseImplTest {

    @Mock
    private ProductDataProvider productDataProvider;

    @Mock
    private SubProductDataProvider subProductDataProvider;

    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCaseImpl;

    @Test
    void shouldCallDataProviderWithCorrectFields() {
        CreateProductDto dto = new CreateProductDto(
                "Name",
                ProductStatus.DISPONIVEL,
                Collections.emptyList()
        );

        createProductUseCaseImpl.execute(dto);

        verify(productDataProvider).create(dto);
    }

    @Test
    void shouldCallSubProductDataProviderWithCorrectFields() {
        SubProduct<Product> subProduct = new SubProduct<>(
                null,
                "Name",
                BigDecimal.ONE,
                null
        );

        CreateProductDto dto = new CreateProductDto(
                "Name",
                ProductStatus.DISPONIVEL,
                Collections.singletonList(subProduct)
        );

        createProductUseCaseImpl.execute(dto);

        verify(productDataProvider).create(dto);
        verify(subProductDataProvider).create(subProduct);
    }
}
