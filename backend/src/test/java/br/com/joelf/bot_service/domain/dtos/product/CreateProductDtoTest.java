package br.com.joelf.bot_service.domain.dtos.product;

import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateProductDtoTest {

     @Test
     public void testCreateProductDtoBuilder() {
         CreateProductDto createProductDto = new CreateProductDto("name", ProductStatus.DISPONIVEL);

         Assertions.assertEquals("name", createProductDto.getName());
         Assertions.assertEquals(ProductStatus.DISPONIVEL, createProductDto.getStatus());
     }

     @Test
        public void testCreateProductDtoSetters() {
            CreateProductDto createProductDto = new CreateProductDto();
            createProductDto.setName("name");
            createProductDto.setStatus(ProductStatus.DISPONIVEL);

            Assertions.assertEquals("name", createProductDto.getName());
            Assertions.assertEquals(ProductStatus.DISPONIVEL, createProductDto.getStatus());
        }
}
