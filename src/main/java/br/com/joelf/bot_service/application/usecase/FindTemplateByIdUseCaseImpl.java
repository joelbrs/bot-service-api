package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.TemplateDataProviderException;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.FindTemplateByIdUseCase;
import br.com.joelf.bot_service.domain.usecase.exceptions.FindTemplateByIdUseCaseException;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class FindTemplateByIdUseCaseImpl implements FindTemplateByIdUseCase {
    private final TemplateDataProvider templateDataProvider;

    @Override
    public Template execute(UUID id) {
        try {
            return templateDataProvider.findById(id);
        } catch (TemplateDataProviderException e) {
            throw new FindTemplateByIdUseCaseException(e.getMessage(), e.getPhase().getHttpStatus());
        }
    }
}
