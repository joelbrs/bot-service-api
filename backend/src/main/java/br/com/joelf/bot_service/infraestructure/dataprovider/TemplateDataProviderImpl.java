package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.TemplateDataProviderException;
import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.dtos.template.UpdateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgTemplateRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgTemplate;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@AllArgsConstructor
public class TemplateDataProviderImpl implements TemplateDataProvider {

    private final ModelMapper modelMapper;
    private final PgTemplateRepository pgTemplateRepository;

    @Override
    public Template create(CreateTemplateDto template) {
        PgTemplate pgTemplate = modelMapper.map(template, PgTemplate.class);
        return modelMapper.map(pgTemplateRepository.save(pgTemplate), Template.class);
    }

    @Override
    public Template update(UUID id, UpdateTemplateDto template) {
        try {
            PgTemplate pgTemplate = pgTemplateRepository.getReferenceById(id);
            BeanUtils.copyProperties(template, pgTemplate, "id");

            return modelMapper.map(pgTemplateRepository.save(pgTemplate), Template.class);
        } catch (EntityNotFoundException e) {
            throw new TemplateDataProviderException("Template not found, id: " + id);
        }
    }

    @Override
    public Page<Template> findAll(Pageable pageable, String name) {
        Page<PgTemplate> pgTemplates = pgTemplateRepository.findAllPaged(pageable, name);
        return pgTemplates.map(pgTemplate -> modelMapper.map(pgTemplate, Template.class));
    }
}
