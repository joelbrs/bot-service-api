package br.com.joelf.bot_service.domain.dtos.template;

import br.com.joelf.bot_service.domain.entities.TemplateStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateTemplateDtoTest {
    @Test
    public void testUpdateTemplateDtoBuilder() {
        UpdateTemplateDto dto = new UpdateTemplateDto("name", "content", TemplateStatus.ATIVO);

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals("content", dto.getContent());
        Assertions.assertEquals(TemplateStatus.ATIVO, dto.getStatus());
    }

    @Test
    public void testUpdateTemplateDtoSetters() {
        UpdateTemplateDto dto = new UpdateTemplateDto();
        dto.setName("name");
        dto.setContent("content");

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals("content", dto.getContent());
    }
}
