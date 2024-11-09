package br.com.joelf.bot_service.domain.usecase;

import java.util.UUID;

public interface DeleteTemplateUseCase {
    void execute(UUID id);
}
