package br.com.joelf.bot_service.application.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductDataProvider {
    Page<Product> findAllPaged(Pageable pageable, String name, ProductStatus status);
    Product create(CreateProductDto product);
    Product update(UUID id, UpdateProductDto product) throws ProductDataProviderException;
    Product findById(UUID id);
    List<Product> findByName(String name);
    void delete(UUID id) throws ProductDataProviderException;
}
