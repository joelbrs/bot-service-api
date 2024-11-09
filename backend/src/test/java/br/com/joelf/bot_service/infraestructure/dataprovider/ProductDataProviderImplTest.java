package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.domain.entities.SubProduct;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductDataProviderImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PgProductRepository pgProductRepository;

    @InjectMocks
    private ProductDataProviderImpl productDataProviderImpl;

    @Test
    void shouldCreateProductCorrectly() {
        CreateProductDto dto = new CreateProductDto("name", ProductStatus.DISPONIVEL, Collections.emptyList());

        UUID generatedId = UUID.randomUUID();

        PgProduct mappedPgProduct = new PgProduct();
        mappedPgProduct.setName(dto.getName());
        mappedPgProduct.setStatus(dto.getStatus());

        mappedPgProduct.setId(generatedId);

        Product expectedProduct =
                new Product(
                        mappedPgProduct.getId(), mappedPgProduct.getName(), mappedPgProduct.getStatus(), Collections.emptyList()
                );

        when(modelMapper.map(dto, PgProduct.class)).thenReturn(mappedPgProduct);
        when(pgProductRepository.save(mappedPgProduct)).thenReturn(mappedPgProduct);
        when(modelMapper.map(mappedPgProduct, Product.class)).thenReturn(expectedProduct);

        Product product = productDataProviderImpl.create(dto);

        Assertions.assertNotNull(product, "Product should not be null");
        Assertions.assertEquals(expectedProduct, product, "Product should be equal to expectedProduct");
    }

    @Test
    void shouldUpdateProductCorrectly() {
        UUID id = UUID.randomUUID();
        UpdateProductDto dto = new UpdateProductDto("name", ProductStatus.DISPONIVEL, Collections.emptyList());

        PgProduct pgProduct = new PgProduct();
        pgProduct.setId(id);

        when(pgProductRepository.getReferenceById(id)).thenReturn(pgProduct);

        Product expectedProduct = new Product(id, dto.getName(), dto.getStatus(), Collections.emptyList());

        when(pgProductRepository.save(pgProduct)).thenReturn(pgProduct);
        when(modelMapper.map(pgProduct, Product.class)).thenReturn(expectedProduct);

        Product product = productDataProviderImpl.update(id, dto);

        Assertions.assertNotNull(product, "Product should not be null");
        Assertions.assertEquals(expectedProduct, product, "Product should be equal to expectedProduct");
    }

    @Test
    void shouldThrowProductDataProviderExceptionWhenProductNotFound() {
        UUID id = UUID.randomUUID();
        UpdateProductDto dto = new UpdateProductDto("name", ProductStatus.DISPONIVEL, Collections.emptyList());

        when(pgProductRepository.getReferenceById(id)).thenThrow(new EntityNotFoundException());

        Assertions.assertThrows(ProductDataProviderException.class, () -> productDataProviderImpl.update(id, dto));
    }

    @Test
    void shouldFindAllPagedCorrectly() {
        Pageable pageable = mock(Pageable.class);

        PgProduct pgProduct = new PgProduct();
        Product expectedProduct = new Product();

        when(pgProductRepository.findAllPaged(pageable, null, null)).thenReturn(new PageImpl<>(List.of(pgProduct)));
        when(modelMapper.map(pgProduct, Product.class)).thenReturn(expectedProduct);

        Page<Product> products = productDataProviderImpl.findAllPaged(pageable, null, null);

        Assertions.assertNotNull(products, "Products should not be null");
        Assertions.assertEquals(1, products.getTotalElements(), "Should have 1 element");
        Assertions.assertNotNull(products.getContent(), "Products content should not be null");
        Assertions.assertEquals(expectedProduct, products.getContent().getFirst(), "Should have 1 element");
    }

    @Test
    void shouldDeleteProductCorrectly() {
        UUID id = UUID.randomUUID();

        when(pgProductRepository.existsById(id)).thenReturn(true);

        productDataProviderImpl.delete(id);

        Assertions.assertDoesNotThrow(() -> productDataProviderImpl.delete(id), "Should not throw any exception");
    }

    @Test
    void shouldThrowProductDataProviderExceptionWhenProductNotFoundOnDelete() {
        UUID id = UUID.randomUUID();

        when(pgProductRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(ProductDataProviderException.class, () -> productDataProviderImpl.delete(id), "Should throw ProductDataProviderException");
    }

    @Test
    void shouldThrowProductDataProviderExceptionWhenProductCannotBeDeleted() {
        UUID id = UUID.randomUUID();

        when(pgProductRepository.existsById(id)).thenReturn(true);
        doThrow(new DataIntegrityViolationException("Data Integrity Violation")).when(pgProductRepository).deleteById(id);

        Assertions.assertThrows(ProductDataProviderException.class, () -> productDataProviderImpl.delete(id), "Should throw ProductDataProviderException");
    }
}
