package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.product.ProductWithSubProductsDto;

import java.util.List;

public interface FindProductsByNameUseCase {
    List<ProductWithSubProductsDto> execute(String name);
}
