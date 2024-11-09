package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        CreateProductDto dto = new CreateProductDto("name", ProductStatus.DISPONIVEL);

        UUID generatedId = UUID.randomUUID();

        PgProduct mappedPgProduct = new PgProduct();
        mappedPgProduct.setName(dto.getName());
        mappedPgProduct.setStatus(dto.getStatus());

        mappedPgProduct.setId(generatedId);

        Product expectedProduct =
                new Product(mappedPgProduct.getId(), mappedPgProduct.getName(), mappedPgProduct.getStatus());

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
        UpdateProductDto dto = new UpdateProductDto("name", ProductStatus.DISPONIVEL);

        PgProduct pgProduct = new PgProduct();
        pgProduct.setId(id);

        when(pgProductRepository.getReferenceById(id)).thenReturn(pgProduct);

        Product expectedProduct = new Product(id, dto.getName(), dto.getStatus());

        when(pgProductRepository.save(pgProduct)).thenReturn(pgProduct);
        when(modelMapper.map(pgProduct, Product.class)).thenReturn(expectedProduct);

        Product product = productDataProviderImpl.update(id, dto);

        Assertions.assertNotNull(product, "Product should not be null");
        Assertions.assertEquals(expectedProduct, product, "Product should be equal to expectedProduct");
    }

    @Test
    void shouldThrowProductDataProviderExceptionWhenProductNotFound() {
        UUID id = UUID.randomUUID();
        UpdateProductDto dto = new UpdateProductDto("name", ProductStatus.DISPONIVEL);

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
        Assertions.assertEquals(expectedProduct, products.getContent().get(0), "Should have 1 element");
    }
}
