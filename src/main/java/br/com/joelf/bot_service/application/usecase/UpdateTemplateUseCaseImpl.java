package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.TemplateDataProviderException;
import br.com.joelf.bot_service.domain.dtos.template.UpdateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.UpdateTemplateUseCase;
import br.com.joelf.bot_service.domain.usecase.exceptions.UpdateTemplateUseCaseException;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UpdateTemplateUseCaseImpl implements UpdateTemplateUseCase {

    private final TemplateDataProvider templateDataProvider;

    @Override
    public Template execute(UUID id, UpdateTemplateDto template) {
        try {
            return templateDataProvider.update(id, template);
        } catch (TemplateDataProviderException e) {
            throw new UpdateTemplateUseCaseException(e.getMessage(), e.getPhase().getHttpStatus());
        }
    }
}
