package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.commom.ExceptionPhase;
import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.TemplateDataProviderException;
import br.com.joelf.bot_service.domain.dtos.template.UpdateTemplateDto;
import br.com.joelf.bot_service.domain.usecase.exceptions.UpdateTemplateUseCaseException;
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
public class UpdateTemplateUseCaseImplTest {

    @Mock
    private TemplateDataProvider templateDataProvider;

    @InjectMocks
    private UpdateTemplateUseCaseImpl updateTemplateUseCase;

    @Test
    void shouldCallProductDataProviderWithCorrectFields() {
         UUID id = UUID.randomUUID();
         UpdateTemplateDto dto = new UpdateTemplateDto();

         updateTemplateUseCase.execute(id, dto);

         verify(templateDataProvider).update(id, dto);
    }

    @Test
    void shouldThrowsExceptionWhenDataProviderThrowsException() {
        UUID id = UUID.randomUUID();
        UpdateTemplateDto dto = new UpdateTemplateDto();

        when(templateDataProvider.update(id, dto)).thenThrow(new TemplateDataProviderException("Error", ExceptionPhase.ENTITY_NOT_FOUND));

        Assertions.assertThrows(UpdateTemplateUseCaseException.class, () -> updateTemplateUseCase.execute(id, dto));
    }
}
