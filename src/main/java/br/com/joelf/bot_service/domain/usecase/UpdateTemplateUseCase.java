package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.template.UpdateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;

import java.util.UUID;

public interface UpdateTemplateUseCase {
    Template execute(UUID id, UpdateTemplateDto template);
}
