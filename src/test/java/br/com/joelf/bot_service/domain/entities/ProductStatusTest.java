package br.com.joelf.bot_service.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductStatusTest {

    @Test
    void testProductStatusParameters() {
        Assertions.assertEquals("DISPONIVEL", ProductStatus.DISPONIVEL.toString());
        Assertions.assertEquals("INDISPONIVEL", ProductStatus.INDISPONIVEL.toString());
    }
}
