package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
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
public class FindAllTemplateUseCaseImplTest {

    @Mock
    private TemplateDataProvider templateDataProvider;

    @InjectMocks
    private FindAllTemplateUseCaseImpl findAllTemplateUseCase;

    @Test
    void shouldCallDataProviderWithCorrectParameters() {
        Pageable pageable = mock(Pageable.class);
        String name = "name";

        findAllTemplateUseCase.execute(pageable, name);

        verify(templateDataProvider).findAll(pageable, name);
    }
}
