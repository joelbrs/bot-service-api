package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.CreateTemplateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TemplateControllerTest {

    @Mock
    private CreateTemplateUseCase createTemplateUseCase;

    @InjectMocks
    private TemplateController templateController;

    @Test
    void shouldCreateTemplateOnSuccess() {
        CreateTemplateDto dto = new CreateTemplateDto();
        Template template = mock(Template.class);

        when(createTemplateUseCase.execute(dto)).thenReturn(template);

        ResponseEntity<Template> result = templateController.create(dto);

        Assertions.assertNotNull(result.getBody(), "Body should not be null");
        Assertions.assertEquals(template, result.getBody(), "Body should be the same as the product");
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode(), "Status code should be CREATED");
    }
}
