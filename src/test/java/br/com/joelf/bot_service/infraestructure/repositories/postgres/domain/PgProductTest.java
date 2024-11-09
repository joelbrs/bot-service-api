package br.com.joelf.bot_service.infraestructure.repositories.postgres.domain;

import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PgProductTest {

    @Test
    public void testProductParameters() {
        UUID id = UUID.randomUUID();

        PgProduct pgProduct = new PgProduct();
        pgProduct.setId(id);
        pgProduct.setName("Name");
        pgProduct.setStatus(ProductStatus.DISPONIVEL);

        Assertions.assertNotNull(pgProduct, "PgProduct should not be null");
        Assertions.assertEquals(id, pgProduct.getId(), "Id should be equal to " + id);
        Assertions.assertEquals("Name", pgProduct.getName(), "Name should be equal to Name");
        Assertions.assertEquals(ProductStatus.DISPONIVEL, pgProduct.getStatus(), "Status should be equal to DISPONIVEL");
    }
}
