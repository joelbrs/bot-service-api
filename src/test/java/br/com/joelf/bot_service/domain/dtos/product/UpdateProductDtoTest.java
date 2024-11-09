package br.com.joelf.bot_service.domain.dtos.product;

import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class UpdateProductDtoTest {
    @Test
    public void testUpdateProductDtoBuilder() {
        UpdateProductDto dto = new UpdateProductDto("name", ProductStatus.DISPONIVEL, Collections.emptyList());

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals(ProductStatus.DISPONIVEL, dto.getStatus());
    }

    @Test
    public void testUpdateProductDtoSetters() {
        UpdateProductDto dto = new UpdateProductDto();
        dto.setName("name");
        dto.setStatus(ProductStatus.DISPONIVEL);

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals(ProductStatus.DISPONIVEL, dto.getStatus());
    }
}
