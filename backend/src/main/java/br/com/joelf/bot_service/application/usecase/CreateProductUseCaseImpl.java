package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.usecase.CreateProductUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductDataProvider productDataProvider;

    @Override
    public Product execute(CreateProductDto product) {
        return productDataProvider.create(product);
    }
}
