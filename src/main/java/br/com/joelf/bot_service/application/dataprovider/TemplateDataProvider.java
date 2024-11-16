package br.com.joelf.bot_service.application.dataprovider;

import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.dtos.template.UpdateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TemplateDataProvider {
    Template create(CreateTemplateDto template);
    Template update(UUID id, UpdateTemplateDto template);
    Template findById(UUID id);
    Page<Template> findAll(Pageable pageable, String name);
    Template findActiveTemplate();
    void delete(UUID id);
    void updateActiveToInactive();
}
