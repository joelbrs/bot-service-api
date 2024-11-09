package br.com.joelf.bot_service.domain.entities;

import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProductTest {

    @Test
    void testProductInstance() {
        Product product = new Product();

        UUID id = UUID.randomUUID();

        product.setId(id);
        product.setName("Name");
        product.setStatus(ProductStatus.DISPONIVEL);

        Assertions.assertEquals(id, product.getId());
        Assertions.assertEquals("Name", product.getName());
        Assertions.assertEquals(ProductStatus.DISPONIVEL, product.getStatus());
    }
}
