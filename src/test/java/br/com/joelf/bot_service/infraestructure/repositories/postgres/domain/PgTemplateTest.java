package br.com.joelf.bot_service.infraestructure.repositories.postgres.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PgTemplateTest {

    @Test
    public void testTempalteParameters() {
        UUID id = UUID.randomUUID();

        PgTemplate pgTemplate = new PgTemplate();
        pgTemplate.setId(id);
        pgTemplate.setName("Name");
        pgTemplate.setContent("Content");

        Assertions.assertNotNull(pgTemplate, "PgProduct should not be null");
        Assertions.assertEquals(id, pgTemplate.getId(), "Id should be equal to " + id);
        Assertions.assertEquals("Name", pgTemplate.getName(), "Name should be equal to Name");
        Assertions.assertEquals("Content", pgTemplate.getContent(), "Content should be equal to Content");
    }
}
