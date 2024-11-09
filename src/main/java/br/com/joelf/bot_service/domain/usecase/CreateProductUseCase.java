package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;

public interface CreateProductUseCase {
    Product execute(CreateProductDto product);
}
