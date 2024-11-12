package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.entities.Template;

import java.util.UUID;

public interface FindTemplateByIdUseCase {
    Template execute(UUID id);
}
