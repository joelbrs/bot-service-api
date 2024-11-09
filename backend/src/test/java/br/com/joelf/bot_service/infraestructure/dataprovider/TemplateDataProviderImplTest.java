package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgTemplateRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TemplateDataProviderImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PgTemplateRepository pgTemplateRepository;

    @InjectMocks
    private TemplateDataProviderImpl templateDataProvider;

    @Test
    void shouldCreateTemplateOnSucces() {
        CreateTemplateDto dto = new CreateTemplateDto("name", "content");

        UUID generatedId = UUID.randomUUID();

        PgTemplate mappedPgTemplate = new PgTemplate();
        mappedPgTemplate.setName(dto.getName());
        mappedPgTemplate.setContent(dto.getContent());

        mappedPgTemplate.setId(generatedId);

        Template expectedTemplate =
                new Template(mappedPgTemplate.getId(), mappedPgTemplate.getName(), mappedPgTemplate.getContent());

        when(modelMapper.map(dto, PgTemplate.class)).thenReturn(mappedPgTemplate);
        when(pgTemplateRepository.save(mappedPgTemplate)).thenReturn(mappedPgTemplate);
        when(modelMapper.map(mappedPgTemplate, Template.class)).thenReturn(expectedTemplate);

        Template template = templateDataProvider.create(dto);

        Assertions.assertNotNull(template, "Template should not be null");
        Assertions.assertEquals(expectedTemplate, template, "Template should be equal to expectedTemplate");
    }
}
