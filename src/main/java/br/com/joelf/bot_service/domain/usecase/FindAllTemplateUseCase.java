package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.entities.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllTemplateUseCase {
    Page<Template> execute(Pageable pageable, String name);
}
