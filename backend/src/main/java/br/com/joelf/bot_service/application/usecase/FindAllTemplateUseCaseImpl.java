package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.FindAllTemplateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class FindAllTemplateUseCaseImpl implements FindAllTemplateUseCase {

    private final TemplateDataProvider templateDataProvider;

    @Override
    public Page<Template> execute(Pageable pageable, String name) {
        return templateDataProvider.findAll(pageable, name);
    }
}
