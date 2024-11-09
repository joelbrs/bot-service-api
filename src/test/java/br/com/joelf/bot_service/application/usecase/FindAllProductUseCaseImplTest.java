package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FindAllProductUseCaseImplTest {

    @Mock
    private ProductDataProvider productDataProvider;

    @InjectMocks
    private FindAllProductUseCaseImpl findAllProductUseCase;

    @Test
    void shouldCallDataProviderWithCorrectParameters() {
        Pageable pageable = mock(Pageable.class);
        String name = "name";
        ProductStatus status = ProductStatus.DISPONIVEL;

        findAllProductUseCase.execute(pageable, name, status);

        verify(productDataProvider).findAllPaged(pageable, name, status);
    }
}
