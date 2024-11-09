package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.usecase.exceptions.UpdateProductUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateProductUseCaseImplTest {

    @Mock
    private ProductDataProvider productDataProvider;

    @InjectMocks
    private UpdateProductUseCaseImpl updateProductUseCase;

    @Test
    void shouldCallProductDataProviderWithCorrectFields() {
        UUID id = UUID.randomUUID();
        UpdateProductDto dto = new UpdateProductDto();

        updateProductUseCase.execute(id, dto);

        verify(productDataProvider).update(id, dto);
    }

    @Test
    void shouldThrowsExceptionWhenProductDataProviderThrowsException() {
        UUID id = UUID.randomUUID();
        UpdateProductDto dto = new UpdateProductDto();

        when(productDataProvider.update(id, dto)).thenThrow(new ProductDataProviderException("Error"));

        Assertions.assertThrows(UpdateProductUseCaseException.class, () -> updateProductUseCase.execute(id, dto));
    }
}
