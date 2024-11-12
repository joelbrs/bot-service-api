package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.product.ProductWithSubProductsDto;

import java.util.UUID;

public interface FindProductByIdUseCase {
    ProductWithSubProductsDto execute(UUID id);
}
