package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.usecase.UpdateProductUseCase;
import br.com.joelf.bot_service.domain.usecase.exceptions.UpdateProductUseCaseException;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductDataProvider productDataProvider;

    @Override
    public Product execute(UUID id, UpdateProductDto product) {
        try {
            return productDataProvider.update(id, product);
        } catch (ProductDataProviderException e) {
            throw new UpdateProductUseCaseException(e.getMessage());
        }
    }
}
