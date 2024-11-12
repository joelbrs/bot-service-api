package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.entities.TemplateStatus;
import br.com.joelf.bot_service.domain.usecase.CreateTemplateUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTemplateUseCaseImpl implements CreateTemplateUseCase {

    private final TemplateDataProvider templateDataProvider;

    @Transactional
    @Override
    public Template execute(CreateTemplateDto template) {
        if (template.getStatus().equals(TemplateStatus.ATIVO)) {
            templateDataProvider.updateActiveToInactive();
        }
        return templateDataProvider.create(template);
    }
}
