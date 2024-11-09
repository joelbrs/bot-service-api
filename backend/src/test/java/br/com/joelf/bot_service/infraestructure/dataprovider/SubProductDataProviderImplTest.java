package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.SubProduct;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgSubProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgSubProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubProductDataProviderImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PgSubProductRepository pgSubProductRepository;

    @InjectMocks
    private SubProductDataProviderImpl subProductDataProviderImpl;

    @Test
    void shouldCreateSubProductCorrectly() {

        Product product = new Product();
        SubProduct<Product> subProduct = new SubProduct<>(
                null,
                "name",
                BigDecimal.ONE,
                product
        );

        PgProduct pgProduct = new PgProduct();
        PgSubProduct pgSubProduct = new PgSubProduct();

        when(modelMapper.map(subProduct.getProduct(), PgProduct.class)).thenReturn(pgProduct);
        when(modelMapper.map(subProduct, PgSubProduct.class)).thenReturn(pgSubProduct);
        when(pgSubProductRepository.save(pgSubProduct)).thenReturn(pgSubProduct);

        Assertions.assertDoesNotThrow(() -> subProductDataProviderImpl.create(subProduct), "Should not throw any exception");
    }

    @Test
    void shouldDeleteAllByProductIdCorrectly() {
        UUID id = UUID.randomUUID();
        Assertions.assertDoesNotThrow(() -> subProductDataProviderImpl.deleteAllByProductId(id), "Should not throw any exception");
    }

    @Test
    void shouldThrowExceptionWhenDeleteAllByProductId() {
        UUID id = UUID.randomUUID();
        doThrow(new DataIntegrityViolationException("Error")).when(pgSubProductRepository).deleteAllByProductId(id);
        Assertions.assertThrows(RuntimeException.class, () -> subProductDataProviderImpl.deleteAllByProductId(id), "Should throw an exception");
    }
}
