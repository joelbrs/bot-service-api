package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.exceptions.TemplateDataProviderException;
import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.dtos.template.UpdateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.entities.TemplateStatus;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgTemplateRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgTemplate;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

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
        CreateTemplateDto dto = new CreateTemplateDto("name", "content", TemplateStatus.INATIVO);

        UUID generatedId = UUID.randomUUID();

        PgTemplate mappedPgTemplate = new PgTemplate();
        mappedPgTemplate.setName(dto.getName());
        mappedPgTemplate.setContent(dto.getContent());
        mappedPgTemplate.setStatus(dto.getStatus());

        mappedPgTemplate.setId(generatedId);

        Template expectedTemplate =
                new Template(
                        mappedPgTemplate.getId(),
                        mappedPgTemplate.getName(),
                        mappedPgTemplate.getContent(),
                        mappedPgTemplate.getStatus()
                );

        when(modelMapper.map(dto, PgTemplate.class)).thenReturn(mappedPgTemplate);
        when(pgTemplateRepository.save(mappedPgTemplate)).thenReturn(mappedPgTemplate);
        when(modelMapper.map(mappedPgTemplate, Template.class)).thenReturn(expectedTemplate);

        Template template = templateDataProvider.create(dto);

        Assertions.assertNotNull(template, "Template should not be null");
        Assertions.assertEquals(expectedTemplate, template, "Template should be equal to expectedTemplate");
    }

    @Test
    void shouldUpdateTemplateOnSucces() {
        UUID id = UUID.randomUUID();
        UpdateTemplateDto dto = new UpdateTemplateDto("name", "content", TemplateStatus.ATIVO);

        PgTemplate mappedPgTemplate = new PgTemplate();
        mappedPgTemplate.setName(dto.getName());
        mappedPgTemplate.setContent(dto.getContent());
        mappedPgTemplate.setStatus(dto.getStatus());

        when(pgTemplateRepository.getReferenceById(id)).thenReturn(mappedPgTemplate);

        Template expectedTemplate =
                new Template(
                        mappedPgTemplate.getId(),
                        mappedPgTemplate.getName(),
                        mappedPgTemplate.getContent(),
                        mappedPgTemplate.getStatus()
                );

        when(pgTemplateRepository.save(mappedPgTemplate)).thenReturn(mappedPgTemplate);
        when(modelMapper.map(mappedPgTemplate, Template.class)).thenReturn(expectedTemplate);

        Template template = templateDataProvider.update(id, dto);

        Assertions.assertNotNull(template, "Template should not be null");
        Assertions.assertEquals(expectedTemplate, template, "Template should be equal to expectedTemplate");
    }

    @Test
    void shouldThrowDataProviderExceptionWhenTemplateNotFound() {
        UUID id = UUID.randomUUID();
        UpdateTemplateDto dto = new UpdateTemplateDto("name", "content", TemplateStatus.ATIVO);

        when(pgTemplateRepository.getReferenceById(id)).thenThrow(new EntityNotFoundException());

        Assertions.assertThrows(TemplateDataProviderException.class, () -> templateDataProvider.update(id, dto));
    }

    @Test
    void shouldFindAllTemplatesOnSucces() {
        Pageable pageable = mock(Pageable.class);

        PgTemplate pgTemplate = new PgTemplate();
        Template expectedTemplate = new Template();

        when(pgTemplateRepository.findAllPaged(pageable, null)).thenReturn(new PageImpl<>(List.of(pgTemplate)));
        when(modelMapper.map(pgTemplate, Template.class)).thenReturn(expectedTemplate);

        Page<Template> templates = templateDataProvider.findAll(pageable, null);

        Assertions.assertNotNull(templates, "Templates should not be null");
        Assertions.assertEquals(1, templates.getTotalElements(), "Should have 1 element");
        Assertions.assertNotNull(templates.getContent(), "Templates content should not be null");
        Assertions.assertEquals(expectedTemplate, templates.getContent().getFirst(), "Should have 1 element");
    }

    @Test
    void shouldDeleteTemplateCorrectly() {
        UUID id = UUID.randomUUID();

        when(pgTemplateRepository.existsById(id)).thenReturn(true);

        templateDataProvider.delete(id);

        Assertions.assertDoesNotThrow(() -> templateDataProvider.delete(id));
    }

    @Test
    void shouldThrowsDataProviderExceptionWhenTemplateNotFound() {
        UUID id = UUID.randomUUID();

        when(pgTemplateRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(TemplateDataProviderException.class, () -> templateDataProvider.delete(id));
    }

    @Test
    void shouldThrowsDataProviderExceptionWhenTemplateCannotBeDeleted() {
        UUID id = UUID.randomUUID();

        when(pgTemplateRepository.existsById(id)).thenReturn(true);
        doThrow(new DataIntegrityViolationException("Error")).when(pgTemplateRepository).deleteById(id);

        Assertions.assertThrows(TemplateDataProviderException.class, () -> templateDataProvider.delete(id));
    }
}
