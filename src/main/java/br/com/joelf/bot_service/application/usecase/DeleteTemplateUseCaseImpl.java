package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.TemplateDataProviderException;
import br.com.joelf.bot_service.domain.usecase.DeleteTemplateUseCase;
import br.com.joelf.bot_service.domain.usecase.exceptions.DeleteTemplateUseCaseException;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class DeleteTemplateUseCaseImpl implements DeleteTemplateUseCase {

    private final TemplateDataProvider deleteTemplateUseCase;

    @Override
    public void execute(UUID id) {
        try {
            deleteTemplateUseCase.delete(id);
        } catch (TemplateDataProviderException e) {
            throw new DeleteTemplateUseCaseException(e.getMessage());
        }
    }
}
