package br.com.joelf.bot_service.domain.dtos.template;

import br.com.joelf.bot_service.domain.entities.TemplateStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateTemplateDtoTest {
    @Test
    public void testCreateTemplateDtoBuilder() {
        CreateTemplateDto dto = new CreateTemplateDto("name", "content", TemplateStatus.ATIVO);

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals("content", dto.getContent());
        Assertions.assertEquals(TemplateStatus.ATIVO, dto.getStatus());
    }

    @Test
    public void testCreateTemplateDtoSetters() {
        CreateTemplateDto dto = new CreateTemplateDto();
        dto.setName("name");
        dto.setContent("content");
        dto.setStatus(TemplateStatus.INATIVO);

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals("content", dto.getContent());
        Assertions.assertEquals(TemplateStatus.INATIVO, dto.getStatus());
    }
}
