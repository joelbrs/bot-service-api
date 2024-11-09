package br.com.joelf.bot_service.application.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;

import java.util.UUID;

public interface ProductDataProvider {
    Product create(CreateProductDto product);
    Product update(UUID id, UpdateProductDto product) throws ProductDataProviderException;
}
