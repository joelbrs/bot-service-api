package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;

public interface CreateTemplateUseCase {
    Template execute(CreateTemplateDto template);
}
