package br.com.joelf.bot_service.domain.dtos.template;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateTemplateDtoTest {
    @Test
    public void testCreateTemplateDtoBuilder() {
        CreateTemplateDto dto = new CreateTemplateDto("name", "content");

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals("content", dto.getContent());
    }

    @Test
    public void testCreateTemplateDtoSetters() {
        CreateTemplateDto dto = new CreateTemplateDto();
        dto.setName("name");
        dto.setContent("content");

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals("content", dto.getContent());
    }
}
