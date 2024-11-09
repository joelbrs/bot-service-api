package br.com.joelf.bot_service.application.dataprovider;

import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;

public interface TemplateDataProvider {
    Template create(CreateTemplateDto template);
}
