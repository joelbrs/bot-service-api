package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseImplTest {

    @Mock
    private ProductDataProvider productDataProvider;

    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCaseImpl;

    @Test
    void shouldCallDataProviderWithCorrectFields() {
        CreateProductDto dto = new CreateProductDto();

        createProductUseCaseImpl.execute(dto);

        verify(productDataProvider).create(dto);
    }
}
