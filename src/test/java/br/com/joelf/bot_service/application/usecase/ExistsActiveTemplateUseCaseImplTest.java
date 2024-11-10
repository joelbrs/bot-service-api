package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExistsActiveTemplateUseCaseImplTest {

    @Mock
    private TemplateDataProvider templateDataProvider;

    @InjectMocks
    private ExistsActiveTemplateUseCaseImpl existsActiveTemplateUseCase;

    @Test
    void shouldReturnTrueWhenActiveTemplateExists() {
        when(templateDataProvider.existsActiveTemplate()).thenReturn(Boolean.TRUE);

        Assertions.assertTrue(existsActiveTemplateUseCase.execute());
    }
}
