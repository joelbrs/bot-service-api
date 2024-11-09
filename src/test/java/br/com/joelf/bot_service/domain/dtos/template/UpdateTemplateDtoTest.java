package br.com.joelf.bot_service.domain.dtos.template;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateTemplateDtoTest {
    @Test
    public void testUpdateTemplateDtoBuilder() {
        UpdateTemplateDto dto = new UpdateTemplateDto("name", "content");

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals("content", dto.getContent());
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
