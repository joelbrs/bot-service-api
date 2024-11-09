package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.domain.usecase.CreateProductUseCase;
import br.com.joelf.bot_service.domain.usecase.DeleteProductUseCase;
import br.com.joelf.bot_service.domain.usecase.FindAllProductUseCase;
import br.com.joelf.bot_service.domain.usecase.UpdateProductUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private FindAllProductUseCase findAllProductUseCase;

    @Mock
    private CreateProductUseCase createProductUseCase;

    @Mock
    private UpdateProductUseCase updateProductUseCase;

    @Mock
    private DeleteProductUseCase deleteProductUseCase;

    @InjectMocks
    private ProductController productController;

    @Test
    void shouldFindAllProductsOnSuccess() {
        Pageable pageable = mock(Pageable.class);
        String name = "product";
        ProductStatus status = ProductStatus.DISPONIVEL;

        when(findAllProductUseCase.execute(pageable, name, status)).thenReturn(new PageImpl<>(Collections.emptyList()));

        ResponseEntity<Page<Product>> result =
                productController.findAllPaged(pageable, name, status);

        Assertions.assertNotNull(result.getBody(), "Body should not be null");
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode(), "Status code should be OK");
        Assertions.assertEquals(0, result.getBody().getTotalElements(), "Total elements should be 0");
    }

    @Test
    void shouldCreateProductOnSuccess() {
        CreateProductDto dto = new CreateProductDto();
        Product product = mock(Product.class);

        when(createProductUseCase.execute(dto)).thenReturn(product);

        ResponseEntity<Product> result = productController.create(dto);

        Assertions.assertNotNull(result.getBody(), "Body should not be null");
        Assertions.assertEquals(product, result.getBody(), "Body should be the same as the product");
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode(), "Status code should be CREATED");
    }

    @Test
    void shouldUpdateProductOnSuccess() {
        UpdateProductDto dto = new UpdateProductDto();
        Product product = mock(Product.class);

        when(updateProductUseCase.execute(product.getId(), dto)).thenReturn(product);

        ResponseEntity<Product> result = productController.update(product.getId(), dto);

        Assertions.assertNotNull(result.getBody(), "Body should not be null");
        Assertions.assertEquals(product, result.getBody(), "Body should be the same as the product");
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode(), "Status code should be OK");
    }

    @Test
    void shouldDeleteProductOnSuccess() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> result = productController.delete(id);

        Assertions.assertNull(result.getBody(), "Body should be null");
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode(), "Status code should be NO_CONTENT");
    }
}
