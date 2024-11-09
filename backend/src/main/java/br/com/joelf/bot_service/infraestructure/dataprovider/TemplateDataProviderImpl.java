package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgTemplateRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgTemplate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class TemplateDataProviderImpl implements TemplateDataProvider {

    private final ModelMapper modelMapper;
    private final PgTemplateRepository pgTemplateRepository;

    @Override
    public Template create(CreateTemplateDto template) {
        PgTemplate pgTemplate = modelMapper.map(template, PgTemplate.class);
        return modelMapper.map(pgTemplateRepository.save(pgTemplate), Template.class);
    }
}
