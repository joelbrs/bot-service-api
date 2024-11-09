package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.dtos.template.UpdateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.CreateTemplateUseCase;
import br.com.joelf.bot_service.domain.usecase.DeleteTemplateUseCase;
import br.com.joelf.bot_service.domain.usecase.FindAllTemplateUseCase;
import br.com.joelf.bot_service.domain.usecase.UpdateTemplateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TemplateControllerTest {

    @Mock
    private CreateTemplateUseCase createTemplateUseCase;

    @Mock
    private UpdateTemplateUseCase updateTemplateUseCase;

    @Mock
    private FindAllTemplateUseCase findAllTemplateUseCase;

    @Mock
    private DeleteTemplateUseCase deleteTemplateUseCase;

    @InjectMocks
    private TemplateController templateController;

    @Test
    void shouldCreateTemplateOnSuccess() {
        CreateTemplateDto dto = new CreateTemplateDto();
        Template template = mock(Template.class);

        when(createTemplateUseCase.execute(dto)).thenReturn(template);

        ResponseEntity<Template> result = templateController.create(dto);

        Assertions.assertNotNull(result.getBody(), "Body should not be null");
        Assertions.assertEquals(template, result.getBody(), "Body should be the same as the template");
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode(), "Status code should be CREATED");
    }

    @Test
    void shouldUpdateTemplateOnSuccess() {
        UUID id = UUID.randomUUID();
        UpdateTemplateDto dto = new UpdateTemplateDto();
        Template template = mock(Template.class);

        when(updateTemplateUseCase.execute(id, dto)).thenReturn(template);

        ResponseEntity<Template> result = templateController.update(id, dto);

        Assertions.assertNotNull(result.getBody(), "Body should not be null");
        Assertions.assertEquals(template, result.getBody(), "Body should be the same as the template");
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode(), "Status code should be OK");
    }

    @Test
    void shouldFindAllTemplatesOnSuccess() {
        Pageable pageable = mock(Pageable.class);
        String name = "name";
        Template template = mock(Template.class);

        when(findAllTemplateUseCase.execute(pageable, name)).thenReturn(new PageImpl<>(List.of(template)));

        ResponseEntity<Page<Template>> result = templateController.findAll(pageable, name);

        Assertions.assertNotNull(result.getBody(), "Body should not be null");
        Assertions.assertEquals(List.of(template), result.getBody().toList(), "Body should contain the template");
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode(), "Status code should be OK");
    }

    @Test
    void shouldDeleteTemplateOnSuccess() {
        UUID id = UUID.randomUUID();

        templateController.delete(id);

        Assertions.assertDoesNotThrow(() -> deleteTemplateUseCase.execute(id));
    }
}
