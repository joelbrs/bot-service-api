package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.TemplateDataProviderException;
import br.com.joelf.bot_service.domain.usecase.exceptions.DeleteTemplateUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteTemplateUseCaseImplTest {

    @Mock
    private TemplateDataProvider templateDataProvider;

    @InjectMocks
    private DeleteTemplateUseCaseImpl deleteTemplateUseCaseImpl;

    @Test
    void shouldCallDataProviderWithCorrectFields() {
        UUID id = UUID.randomUUID();

        deleteTemplateUseCaseImpl.execute(id);

        verify(templateDataProvider).delete(id);
    }

    @Test
    void shouldThrowsExceptionWhenDataProviderThrowsException() {
        UUID id = UUID.randomUUID();

        doThrow(new TemplateDataProviderException("Error")).when(templateDataProvider).delete(id);

        Assertions.assertThrows(DeleteTemplateUseCaseException.class, () -> deleteTemplateUseCaseImpl.execute(id));
    }
}
