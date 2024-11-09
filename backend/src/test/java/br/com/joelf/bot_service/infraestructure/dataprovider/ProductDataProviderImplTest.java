package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.UUID;

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
}
