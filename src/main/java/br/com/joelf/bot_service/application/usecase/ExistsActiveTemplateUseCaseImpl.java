package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.usecase.ExistsActiveTemplateUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistsActiveTemplateUseCaseImpl implements ExistsActiveTemplateUseCase {

    private final TemplateDataProvider templateDataProvider;

    @Override
    public Boolean execute() {
        return templateDataProvider.existsActiveTemplate();
    }
}
