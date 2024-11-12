package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.FindTemplateByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class FindTemplateByIdUseCaseImpl implements FindTemplateByIdUseCase {
    private final TemplateDataProvider templateDataProvider;

    @Override
    public Template execute(UUID id) {
        return templateDataProvider.findById(id);
    }
}
