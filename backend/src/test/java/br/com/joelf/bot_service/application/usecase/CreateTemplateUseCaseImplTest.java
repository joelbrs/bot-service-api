package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateTemplateUseCaseImplTest {

    @Mock
    private TemplateDataProvider templateDataProvider;

    @InjectMocks
    private CreateTemplateUseCaseImpl createTemplateUseCaseImpl;

    @Test
    void shouldCallDataProviderWithCorrectFields() {
         CreateTemplateDto dto = new CreateTemplateDto();

         createTemplateUseCaseImpl.execute(dto);

         verify(templateDataProvider).create(dto);
    }
}
