package br.com.joelf.bot_service.domain.dtos.product;

import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateProductDtoTest {

     @Test
     public void testCreateProductDtoBuilder() {
         CreateProductDto dto = new CreateProductDto("name", ProductStatus.DISPONIVEL);

         Assertions.assertEquals("name", dto.getName());
         Assertions.assertEquals(ProductStatus.DISPONIVEL, dto.getStatus());
     }

     @Test
     public void testCreateProductDtoSetters() {
         CreateProductDto dto = new CreateProductDto();
         dto.setName("name");
         dto.setStatus(ProductStatus.DISPONIVEL);

         Assertions.assertEquals("name", dto.getName());
         Assertions.assertEquals(ProductStatus.DISPONIVEL, dto.getStatus());
     }
}
