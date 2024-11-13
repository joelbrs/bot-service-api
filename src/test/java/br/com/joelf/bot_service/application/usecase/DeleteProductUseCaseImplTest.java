package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.commom.ExceptionPhase;
import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.usecase.exceptions.DeleteProductUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteProductUseCaseImplTest {

    @Mock
    private ProductDataProvider productDataProvider;

    @InjectMocks
    private DeleteProductUseCaseImpl deleteProductUseCase;

    @Test
    void shouldCallProductDataProviderWithCorrectFields() {
         UUID id = UUID.randomUUID();

         deleteProductUseCase.execute(id);

         verify(productDataProvider).delete(id);
    }

    @Test
    void shouldThrowsExceptionWhenProductDataProviderThrowsException() {
         UUID id = UUID.randomUUID();

         doThrow(new ProductDataProviderException("Error", ExceptionPhase.DATA_INTEGRITY)).when(productDataProvider).delete(id);

         Assertions.assertThrows(DeleteProductUseCaseException.class, () -> deleteProductUseCase.execute(id));
    }
}
