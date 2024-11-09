package br.com.joelf.bot_service.application.dataprovider;

import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;

public interface ProductDataProvider {
    Product create(CreateProductDto product);
}
