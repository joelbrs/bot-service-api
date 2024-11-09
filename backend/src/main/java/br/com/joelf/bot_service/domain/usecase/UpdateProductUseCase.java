package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;

import java.util.UUID;

public interface UpdateProductUseCase {
    Product execute(UUID id, UpdateProductDto product);
}
