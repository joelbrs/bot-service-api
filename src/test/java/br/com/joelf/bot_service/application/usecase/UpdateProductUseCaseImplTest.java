package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.commom.ExceptionPhase;
import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.domain.entities.SubProduct;
import br.com.joelf.bot_service.domain.usecase.exceptions.UpdateProductUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateProductUseCaseImplTest {

    @Mock
    private ProductDataProvider productDataProvider;

    @Mock
    private SubProductDataProvider subProductDataProvider;

    @InjectMocks
    private UpdateProductUseCaseImpl updateProductUseCase;

    @Test
    void shouldCallProductDataProviderWithCorrectFields() {
        UUID id = UUID.randomUUID();
        UpdateProductDto dto = new UpdateProductDto(
                "Name",
                ProductStatus.DISPONIVEL,
                Collections.emptyList()
        );

        updateProductUseCase.execute(id, dto);

        verify(productDataProvider).update(id, dto);
    }

    @Test
    void shouldThrowsExceptionWhenProductDataProviderThrowsException() {
        UUID id = UUID.randomUUID();
        UpdateProductDto dto = new UpdateProductDto();

        when(productDataProvider.update(id, dto)).thenThrow(new ProductDataProviderException("Error", ExceptionPhase.DATA_INTEGRITY));

        Assertions.assertThrows(UpdateProductUseCaseException.class, () -> updateProductUseCase.execute(id, dto));
    }

    @Test
    void shouldCallSubProductDataProviderWithCorrectFields() {
        UUID id = UUID.randomUUID();

        SubProduct<Product> subProduct = new SubProduct<>(
                null,
                "Name",
                BigDecimal.ONE,
                null
        );
        UpdateProductDto dto = new UpdateProductDto(
                "Name",
                ProductStatus.DISPONIVEL,
                Collections.singletonList(subProduct)
        );

        updateProductUseCase.execute(id, dto);
        verify(subProductDataProvider).deleteAllByProductId(id);
        verify(subProductDataProvider).create(subProduct);
    }
}
