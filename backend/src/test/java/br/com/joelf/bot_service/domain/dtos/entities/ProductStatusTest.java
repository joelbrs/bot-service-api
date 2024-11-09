package br.com.joelf.bot_service.domain.dtos.entities;

import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductStatusTest {

    @Test
    void testProductStatusParameters() {
        Assertions.assertEquals("DISPONIVEL", ProductStatus.DISPONIVEL.toString());
        Assertions.assertEquals("INDISPONIVEL", ProductStatus.INDISPONIVEL.toString());
    }
}
